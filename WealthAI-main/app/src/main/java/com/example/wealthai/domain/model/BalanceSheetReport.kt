package com.example.wealthai.domain.model

data class BalanceSheetReport(
    val cashAndShortTermInvestments: String,
    val totalAssets: String,
    val totalLiabilities: String,
    val totalEquity: String,
    val shareOutstanding: String,
    val date: String
)
