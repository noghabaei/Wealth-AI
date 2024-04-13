package com.example.wealthai.data.remote.dto

import com.squareup.moshi.Json

data class BalanceSheetDto(
    @field:Json(name = "annualReports") val annualReports: List<BalanceSheetReportDto>?,
    @field:Json(name = "quarterlyReports") val quarterlyReports: List<BalanceSheetReportDto>?,
    @field:Json(name = "symbol") val symbol: String?
)
