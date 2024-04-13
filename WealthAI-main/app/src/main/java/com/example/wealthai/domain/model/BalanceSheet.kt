package com.example.wealthai.domain.model

data class BalanceSheet(
    val annualReport: List<BalanceSheetReport>,
    val quarterReport: List<BalanceSheetReport>,
    val symbol: String
)
