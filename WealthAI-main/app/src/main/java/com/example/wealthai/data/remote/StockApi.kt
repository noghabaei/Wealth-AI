package com.example.wealthai.data.remote

import com.example.wealthai.data.remote.dto.BalanceSheetDto
import com.example.wealthai.data.remote.dto.CashFlowDto
import com.example.wealthai.data.remote.dto.CompanyInfoDto
import com.example.wealthai.data.remote.dto.IncomeStatementDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query


interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListing(
        @Query("apikey") apiKey: String = API_KEY1
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    ): CompanyInfoDto

    @GET("query?function=INCOME_STATEMENT")
    suspend fun getIncomeStatement(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    ): IncomeStatementDto

    @GET("query?function=BALANCE_SHEET")
    suspend fun getBalanceSheet(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    ): BalanceSheetDto

    @GET("query?function=CASH_FLOW")
    suspend fun getCashFlow(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY2
    ): CashFlowDto

    companion object {
        const val API_KEY1 = "IK0WBHK9RZSL85SK"
        const val API_KEY2 = "RUQJGO2M8O0ECDGJ"
        const val API_KEY3 = "359LNYQEWO4RND3Z"
        const val API_KEY4 = "2AD8JTVXG8NOFWAT"
        const val API_KEY5 = "0NCMYCTTBW6ZRLEM"
        const val API_KEY6 = "DU1ANF084FOAXXWR"
        const val API_KEY7 = "BCHBJWJX1BI6P27R"
        const val API_KEY8 = "EU11AFOO8YLHOBRG"
        const val API_KEY9 = "098VV63RGMC12TGB"
        const val API_KEY10 = "7OLBISC7AZLPOXIV"
        const val DEMO_API = "demo"
        const val BASE_URL = "https://alphavantage.co"
    }

}