package com.example.wealthai.data.remote.dto

import com.squareup.moshi.Json

data class CashFlowDto(
    @field:Json(name = "annualReports") val annualReports: List<CashFlowReportDto>?,
    @field:Json(name = "quarterlyReports") val quarterlyReports: List<CashFlowReportDto>?,
    @field:Json(name = "symbol") val symbol: String?
)
