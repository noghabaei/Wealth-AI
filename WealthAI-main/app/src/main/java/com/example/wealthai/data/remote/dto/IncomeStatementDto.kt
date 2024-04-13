package com.example.wealthai.data.remote.dto

import com.squareup.moshi.Json

data class IncomeStatementDto(
    @field:Json(name = "annualReports") val annualReports: List<IncomeStatementReportDto>?,
    @field:Json(name = "quarterlyReports") val quarterlyReports: List<IncomeStatementReportDto>?,
    @field:Json(name = "symbol") val symbol: String?
)