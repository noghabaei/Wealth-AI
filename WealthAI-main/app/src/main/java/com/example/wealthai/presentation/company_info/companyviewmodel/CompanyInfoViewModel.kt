package com.example.wealthai.presentation.company_info.companyviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wealthai.domain.repository.StockRepository
import com.example.wealthai.presentation.company_info.companystate.CompanyInfoState
import com.example.wealthai.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
): ViewModel() {

    var companyInfoState by mutableStateOf(CompanyInfoState())

    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            companyInfoState = companyInfoState.copy(isLoading = true)
            val companyInfoResult = async { repository.getCompanyInfo(symbol = symbol) }
            val intradayInfoResult = async { repository.getIntradayInfo(symbol = symbol) }
            val incomeStatementResult = async { repository.getIncomeStatementInfo(symbol = symbol) }
            val balanceSheetResult = async { repository.getBalanceSheet(symbol = symbol) }
            val cashFlowResult = async { repository.getCashFlow(symbol = symbol) }

            when (val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        company = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            when (val result = intradayInfoResult.await()) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        stockInfos = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            when (val result = incomeStatementResult.await()) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        incomeStatementInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            when (val result = balanceSheetResult.await()) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        balanceSheetInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            when (val result = cashFlowResult.await()) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        cashFlowInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun getFormatedNumber(number: Long): String {

        return when {
            number < 0 -> "-${getFormatedNumber(-number)}"
            number >= 1_000_000_000_000 -> String.format("%.1fT", number / 1_000_000_000_000.0)
            number >= 1_000_000_000 -> String.format("%.1fB", number / 1_000_000_000.0)
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
            number >= 1_000 -> String.format("%.1fk", number / 1_000.0)
            else -> number.toString()
        }
    }

    fun getYear(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val date = LocalDate.parse(dateString, inputFormatter)

        return date.year.toString()
    }

    fun getMonthYear(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val date = LocalDate.parse(dateString, inputFormatter)

        val year = date.year.toString()
        val monthYear = date.month.toString().capitalize(Locale.ROOT) + " " + year

        return monthYear
    }

    fun calculatePerChange(currentClose: Float, previousClose: Float): String {
        val change = ((currentClose-previousClose)*100)/currentClose
        return String.format("%.2f", change)
    }

    fun calculatePerChangeLong(previousValue: Float, currentValue: Float): Float {
        val change = (((abs(currentValue) - abs(previousValue))/previousValue)*100)
        return String.format("%.2f", change).toFloat()
    }

}