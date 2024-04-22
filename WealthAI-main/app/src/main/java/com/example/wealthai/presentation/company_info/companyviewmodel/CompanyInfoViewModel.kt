package com.example.wealthai.presentation.company_info.companyviewmodel

import android.util.Log
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
//    private val symbol: String,
    private val repository: StockRepository
): ViewModel() {

    var companyInfoState by mutableStateOf(CompanyInfoState())

//    private var symbol: String = ""
//
//    fun initSymbol(symbol: String) {
//        this.symbol = symbol
//    }

    fun getComInfo(symbol: String) {
        viewModelScope.launch {
            when (val companyInfoResult = repository.getCompanyInfo(symbol)) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        company = companyInfoResult.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = companyInfoResult.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun getIntraInfo(symbol: String) {
        viewModelScope.launch {
            when (val intradayInfoResult = repository.getIntradayInfo(symbol = symbol)) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        stockInfos = intradayInfoResult.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = intradayInfoResult.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun getIncomeInfo(symbol: String) {
        viewModelScope.launch {
            when (val incomeStatementResult = repository.getIncomeStatementInfo(symbol = symbol)) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        incomeStatementInfo = incomeStatementResult.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = incomeStatementResult.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun getBalanceInfo(symbol: String) {
        viewModelScope.launch {
            when (val balanceSheetResult = repository.getBalanceSheet(symbol = symbol)) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        balanceSheetInfo = balanceSheetResult.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = balanceSheetResult.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun getCashInfo(symbol: String) {
        viewModelScope.launch {
            when (val cashFlowResult = repository.getCashFlow(symbol = symbol)) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        cashFlowInfo = cashFlowResult.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    companyInfoState = companyInfoState.copy(
                        isLoading = false,
                        error = cashFlowResult.message,
                        company = null
                    )
                }
                else -> Unit
            }
        }
    }

    fun getDailyInfo(symbol: String) {
        viewModelScope.launch {
            val dailyInfoResult = repository.getDailyInfo(symbol = symbol)
        }
    }

//    init {
    fun getInfos(symbol: String) {
        viewModelScope.launch {
//            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
//            val symbol = "TSLA"
            Log.d("Symbol", symbol)
            companyInfoState = companyInfoState.copy(isLoading = true)
            val companyInfoResult = repository.getCompanyInfo(symbol = symbol)
            val intradayInfoResult = repository.getIntradayInfo(symbol = symbol)
            val incomeStatementResult = repository.getIncomeStatementInfo(symbol = symbol)
            val balanceSheetResult = repository.getBalanceSheet(symbol = symbol)
            val cashFlowResult = repository.getCashFlow(symbol = symbol)
            val dailyInfoResult = repository.getDailyInfo(symbol = symbol)
            when (val result = companyInfoResult) {
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

            when (val result = intradayInfoResult) {
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

            when (val result = dailyInfoResult) {
                is Resource.Success -> {
                    companyInfoState = companyInfoState.copy(
                        dailyInfos = result.data ?: emptyList(),
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

            when (val result = incomeStatementResult) {
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

            when (val result = balanceSheetResult) {
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

            when (val result = cashFlowResult) {
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
    fun getCompanyInfo(){
        viewModelScope.launch {
            repository.getIntradayInfo("TSLA")
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