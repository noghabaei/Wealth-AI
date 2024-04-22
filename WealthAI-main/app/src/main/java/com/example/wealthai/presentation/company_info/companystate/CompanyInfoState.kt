package com.example.wealthai.presentation.company_info.companystate

import com.example.wealthai.domain.model.BalanceSheet
import com.example.wealthai.domain.model.CashFlow
import com.example.wealthai.domain.model.CompanyInfo
import com.example.wealthai.domain.model.DailyInfo
import com.example.wealthai.domain.model.IncomeStatement
import com.example.wealthai.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val dailyInfos: List<DailyInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val incomeStatementInfo: IncomeStatement? = null,
    val balanceSheetInfo: BalanceSheet? = null,
    val cashFlowInfo: CashFlow? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
