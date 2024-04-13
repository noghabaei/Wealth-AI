package com.example.wealthai.domain.model

data class CompanyInfo(
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String,
    val marketCap: String,
    val peRatio: String,
    val dividendYield: String,
    val primaryExchange: String
)