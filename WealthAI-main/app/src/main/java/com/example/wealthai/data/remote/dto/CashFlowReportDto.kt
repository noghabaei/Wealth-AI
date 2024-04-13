package com.example.wealthai.data.remote.dto

import com.squareup.moshi.Json

data class CashFlowReportDto(
    @field:Json(name = "netIncome") val netIncome: String?,
    @field:Json(name = "operatingCashflow") val cashForOperations: String?,
    @field:Json(name = "cashflowFromInvestment") val cashForInvesting: String?,
    @field:Json(name = "cashflowFromFinancing") val cashForFinancing: String?,
    @field:Json(name = "fiscalDateEnding") val date: String?
)
