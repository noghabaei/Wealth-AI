package com.example.wealthai.presentation.company_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wealthai.presentation.company_info.companyviewmodel.CompanyInfoViewModel
import com.example.wealthai.ui.theme.GreenLight
import com.example.wealthai.ui.theme.Purple200
import com.example.wealthai.ui.theme.RedLight
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Destination
fun CompanyInfoScreen(
    symbol: String,
    viewModel: CompanyInfoViewModel = hiltViewModel()
) {
    val state = viewModel.companyInfoState

    if (state.error == null) {
        state.company?.let { company ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                company.symbol,
                                style = MaterialTheme.typography.h1,
                                color = Color.White
                            )
                        },
                        backgroundColor = Purple200,
                        contentColor = Color.White,
                        elevation = AppBarDefaults.TopAppBarElevation
                    )
                }
            ) {innerPadding->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                ) {
                    if (state.stockInfos.isNotEmpty()) {
                        val currentClose =
                            state.stockInfos[state.stockInfos.size - 1].close.toFloat()
                        val previousClose = state.stockInfos[0].close.toFloat()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            Arrangement.Start,
                            Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$${currentClose}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Chip(
                                onClick = { /* Do something! */ },
                                colors = ChipDefaults.chipColors(
                                    if (currentClose - previousClose < 0) {
                                        RedLight
                                    }else {
                                        GreenLight
                                    }
                                ),
                                leadingIcon = {
                                    if (currentClose - previousClose < 0) {
                                        Icon(
                                            Icons.Default.ArrowDownward,
                                            contentDescription = "Change",
                                            tint = Color.Red
                                        )
                                    } else {
                                        Icon(
                                            Icons.Default.ArrowUpward,
                                            contentDescription = "Change",
                                            tint = Color.Green
                                        )
                                    }
                                },

                            ) {
                                if (currentClose - previousClose < 0) {
                                    Text(
                                        text = viewModel.calculatePerChange(
                                            currentClose = currentClose,
                                            previousClose = previousClose
                                        ),
                                        fontSize = 20.sp,
                                        color = Color.Red
                                    )
                                } else {
                                    Text(
                                        text = viewModel.calculatePerChange(
                                            currentClose = currentClose,
                                            previousClose = previousClose
                                        ),
                                        fontSize = 20.sp,
                                        color = Color.Green
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(6.dp))
                            val amountChange = currentClose - previousClose

                            if (currentClose - previousClose < 0) {
                                Text(
                                    text = String.format("%.2f", amountChange),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.Red
                                )
                            } else {
                                Text(
                                    text = String.format("%.2f", amountChange),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 20.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    color = Color.Green
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Market Summery")
                        Spacer(modifier = Modifier.height(20.dp))

                        if (previousClose <= currentClose) {
                            StockChart(
                                infos = state.stockInfos,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .align(CenterHorizontally),
                                graphColor = Color.Green
                            )

                        } else {
                            StockChart(
                                infos = state.stockInfos,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .align(CenterHorizontally),
                                graphColor = Color.Red
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))

                    }
                    Divider(modifier = Modifier.fillMaxWidth())

                    if (state.stockInfos.isEmpty() || state.incomeStatementInfo?.annualReports?.isEmpty() == true || state.balanceSheetInfo?.quarterReport?.isEmpty() == true || state.cashFlowInfo?.quarterReport?.isEmpty() == true) {

                        Box(modifier = Modifier
                            .fillMaxSize(),
                            Center
                        ){
                            Text(
                                text = "API Limit Reached...",
                                color = Color.Red
                            )
                        }
                    } else {

                        val currentClose = state.stockInfos[state.stockInfos.size - 1].close.toFloat()
                        val previousClose = state.stockInfos[0].close.toFloat()


                        StockHighlights(
                            previousClose = "$$previousClose",
                            dayRange = "$$previousClose - $$currentClose",
                            marketCap = viewModel.getFormatedNumber(state.company.marketCap.toLong()) + " USD",
                            peRatio = state.company.peRatio,
                            dividendYield = (state.company.dividendYield.toFloat() * 100).toString() + "%",
                            primaryExchange = state.company.primaryExchange
                        )

                        Spacer(modifier = Modifier.height(5.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp)
                        )

                        // Income Statement
                        var isIncomeStatementVisible by remember { mutableStateOf(true) }

                        state.incomeStatementInfo?.annualReports?.get(0)?.let { incomeStatement ->
                            val year = viewModel.getYear(incomeStatement.date)
                            val quarter = viewModel.getMonthYear(incomeStatement.date)
                            val revenueYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.incomeStatementInfo.annualReports[1].revenue.toFloat(),
                                currentValue = state.incomeStatementInfo.annualReports[0].revenue.toFloat()
                            )
                            val operatingExpenseYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.incomeStatementInfo.annualReports[1].operatingExpense.toFloat(),
                                currentValue = state.incomeStatementInfo.annualReports[0].operatingExpense.toFloat()
                            )
                            val netIncomeYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.incomeStatementInfo.annualReports[1].netIncome.toFloat(),
                                currentValue = state.incomeStatementInfo.annualReports[0].netIncome.toFloat()
                            )
                            val ebitdaYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.incomeStatementInfo.annualReports[1].editda.toFloat(),
                                currentValue = state.incomeStatementInfo.annualReports[0].editda.toFloat()
                            )
                            val isRevenueIncreasing = revenueYnYChange > 0
                            val isOperatingExpenseIncreasing = operatingExpenseYnYChange > 0
                            val isNetIncomeIncreasing = netIncomeYnYChange > 0
                            val isEbitdaIncreasing = ebitdaYnYChange > 0

                            IncomeStatement(
                                revenue = viewModel.getFormatedNumber(incomeStatement.revenue.toLong()),
                                revenueYnYChange = "$revenueYnYChange%",
                                isRevenueIncreasing = isRevenueIncreasing,
                                operatingExpense = viewModel.getFormatedNumber(incomeStatement.operatingExpense.toLong()),
                                operatingExpenseYnYChange = "$operatingExpenseYnYChange%",
                                isOperatingExpenseIncreasing = isOperatingExpenseIncreasing,
                                netIncome = viewModel.getFormatedNumber(incomeStatement.netIncome.toLong()),
                                netIncomeYnYChange = "$netIncomeYnYChange%",
                                isNetIncomeIncreasing = isNetIncomeIncreasing,
                                ebitda = viewModel.getFormatedNumber(incomeStatement.editda.toLong()),
                                ebitdaYnYChange = "$ebitdaYnYChange%",
                                isEbitdaIncreasing = isEbitdaIncreasing,
                                PreviousYear = year,
                                isVisible = isIncomeStatementVisible,

                            ) {
                                isIncomeStatementVisible = !isIncomeStatementVisible
                            }
                        }

                        Divider(modifier = Modifier.fillMaxWidth())

                        // Balance Sheet
                        var isBalanceSheetVisible by remember { mutableStateOf(false) }

                        state.balanceSheetInfo?.annualReport?.get(0)?.let { balanceSheetReport ->
                            val year = viewModel.getYear(balanceSheetReport.date)
                            val quarter = viewModel.getMonthYear(balanceSheetReport.date)
                            val cashAndShortTermInvestmentYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.balanceSheetInfo.annualReport[1].cashAndShortTermInvestments.toFloat(),
                                currentValue = state.balanceSheetInfo.annualReport[0].cashAndShortTermInvestments.toFloat()
                            )
                            val totalAssetsYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.balanceSheetInfo.annualReport[1].totalAssets.toFloat(),
                                currentValue = state.balanceSheetInfo.annualReport[0].totalAssets.toFloat()
                            )
                            val totalLiabilitiesYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.balanceSheetInfo.annualReport[1].totalLiabilities.toFloat(),
                                currentValue = state.balanceSheetInfo.annualReport[0].totalLiabilities.toFloat()
                            )
                            val totalEquityYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.balanceSheetInfo.annualReport[1].totalEquity.toFloat(),
                                currentValue = state.balanceSheetInfo.annualReport[0].totalEquity.toFloat()
                            )
                            val sharesOutstandingYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.balanceSheetInfo.annualReport[1].shareOutstanding.toFloat(),
                                currentValue = state.balanceSheetInfo.annualReport[0].shareOutstanding.toFloat()
                            )
                            val isCashAndShortTermInvestmentIncreasing = cashAndShortTermInvestmentYnYChange > 0
                            val isTotalAssetIncreasing = totalAssetsYnYChange > 0
                            val isTotalLiabilitiesIncreasing = totalLiabilitiesYnYChange > 0
                            val isTotalEquityIncreasing = totalEquityYnYChange > 0
                            val isSharesOutStandingIncreasing = sharesOutstandingYnYChange > 0

                            BalanceSheet(
                                cashAndShortTermInvestment = viewModel.getFormatedNumber(
                                    balanceSheetReport.cashAndShortTermInvestments.toLong()
                                ),
                                cashAndShortTermInvestmentYnYChange = "$cashAndShortTermInvestmentYnYChange%",
                                isCashAndShortTermInvestmentIncreasing = isCashAndShortTermInvestmentIncreasing,
                                totalAssets = viewModel.getFormatedNumber(balanceSheetReport.totalAssets.toLong()),
                                totalAssetsYnYChange = "$totalAssetsYnYChange%",
                                isTotalAssetsIncreasing = isTotalAssetIncreasing,
                                totalLiabilities = viewModel.getFormatedNumber(balanceSheetReport.totalLiabilities.toLong()),
                                totalLiabilitiesYnYChange = "$totalLiabilitiesYnYChange%",
                                isTotalLiabilitiesIncreasing = isTotalLiabilitiesIncreasing,
                                totalEquity = viewModel.getFormatedNumber(balanceSheetReport.totalEquity.toLong()),
                                totalEquityYnYChange = "$totalEquityYnYChange%",
                                isTotalEquityIncreasing = isTotalEquityIncreasing,
                                sharesOutstanding = viewModel.getFormatedNumber(balanceSheetReport.shareOutstanding.toLong()),
                                sharesOutstandingYnYChange = "$sharesOutstandingYnYChange%",
                                isSharesOutstandingIncreasing = isSharesOutStandingIncreasing,
                                PreviousYear = year,
                                isVisible = isBalanceSheetVisible
                            ) {
                                isBalanceSheetVisible = !isBalanceSheetVisible
                            }

                        }


                        Divider(modifier = Modifier.fillMaxWidth())

                        // Cash Flow
                        var isCashFlowVisible by remember { mutableStateOf(false) }

                        state.cashFlowInfo?.annualReport?.get(0)?.let { cashFlowReport ->
                            val year = viewModel.getYear(cashFlowReport.date)
                            val quarter = viewModel.getMonthYear(cashFlowReport.date)
                            val netIncomeYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.cashFlowInfo.annualReport[1].netIncome.toFloat(),
                                currentValue = state.cashFlowInfo.annualReport[0].netIncome.toFloat()
                            )
                            val cashForOperationsYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.cashFlowInfo.annualReport[1].cashForOperations.toFloat(),
                                currentValue = state.cashFlowInfo.annualReport[0].cashForOperations.toFloat()
                            )
                            val cashForInvestingYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.cashFlowInfo.annualReport[1].cashForInvesting.toFloat(),
                                currentValue = state.cashFlowInfo.annualReport[0].cashForInvesting.toFloat()
                            )
                            val cashForFinancingYnYChange = viewModel.calculatePerChangeLong(
                                previousValue = state.cashFlowInfo.annualReport[1].cashForFinancing.toFloat(),
                                currentValue = state.cashFlowInfo.annualReport[0].cashForFinancing.toFloat()
                            )
                            val isNetIncomeIncreasing = netIncomeYnYChange > 0
                            val isCashForOperationsIncreasing = cashForOperationsYnYChange > 0
                            val isCashForInvestingIncreasing = cashForInvestingYnYChange > 0
                            val isCashForFinanceIncreasing = cashForFinancingYnYChange > 0

                            CashFlow(
                                netIncome = viewModel.getFormatedNumber(cashFlowReport.netIncome.toLong()),
                                netIncomeYnYChange = "$netIncomeYnYChange%",
                                isNetIncomeIncreasing = isNetIncomeIncreasing,
                                cashForOperations = viewModel.getFormatedNumber(cashFlowReport.cashForOperations.toLong()),
                                cashForOperationsYnYChange = "$cashForOperationsYnYChange%",
                                isCashForOperationsIncreasing = isCashForOperationsIncreasing,
                                cashForInvesting = viewModel.getFormatedNumber(cashFlowReport.cashForInvesting.toLong()),
                                cashForInvestingYnYChange = "$cashForInvestingYnYChange%",
                                isCashForInvestmentIncreasing = isCashForInvestingIncreasing,
                                cashForFinancing = viewModel.getFormatedNumber(cashFlowReport.cashForFinancing.toLong()),
                                cashForFinancingYnYChange = "$cashForFinancingYnYChange%",
                                isCashForFinanceIncreasing = isCashForFinanceIncreasing,
                                PreviousYear = year,
                                isVisible = isCashFlowVisible
                            ) {
                                isCashFlowVisible = !isCashFlowVisible
                            }

                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Industry: ${company.industry}",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Country: ${company.country}",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = company.description,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )

                    }

                }

            }
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null) {

            }
        }
    }

}