package com.example.wealthai.data.remote.dto

import com.squareup.moshi.Json

data class BalanceSheetReportDto(
    @field:Json(name = "cashAndShortTermInvestments") val cashAndShortTermInvestments: String?,
    @field:Json(name = "totalAssets") val totalAssets: String?,
    @field:Json(name = "totalLiabilities") val totalLiabilities: String?,
    @field:Json(name = "totalShareholderEquity") val totalEquity: String?,
    @field:Json(name = "commonStockSharesOutstanding") val shareOutstanding: String?,
    @field:Json(name = "fiscalDateEnding") val date: String?
)
