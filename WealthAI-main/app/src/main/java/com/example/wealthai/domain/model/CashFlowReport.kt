package com.example.wealthai.domain.model

data class CashFlowReport(
    val netIncome: String,
    val cashForOperations: String,
    val cashForInvesting: String,
    val cashForFinancing: String,
    val date: String
)
