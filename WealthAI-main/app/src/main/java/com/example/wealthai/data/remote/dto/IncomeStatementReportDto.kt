package com.example.wealthai.data.remote.dto

import com.squareup.moshi.Json

data class IncomeStatementReportDto(
    @field:Json(name = "totalRevenue") val revenue: String?,
    @field:Json(name = "operatingExpenses") val operatingExpenses: String?,
    @field:Json(name = "netIncome") val netIncome: String?,
    @field:Json(name = "ebitda") val editda: String?,
    @field:Json(name = "fiscalDateEnding") val date: String?
)
