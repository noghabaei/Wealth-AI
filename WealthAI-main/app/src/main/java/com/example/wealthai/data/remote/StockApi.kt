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
        @Query("apikey") apiKey: String = API_KEY1
    ): ResponseBody

    @GET("query?function=TIME_SERIES_DAILY&datatype=csv")
    suspend fun getDailyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): CompanyInfoDto

    @GET("query?function=INCOME_STATEMENT")
    suspend fun getIncomeStatement(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): IncomeStatementDto

    @GET("query?function=BALANCE_SHEET")
    suspend fun getBalanceSheet(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): BalanceSheetDto

    @GET("query?function=CASH_FLOW")
    suspend fun getCashFlow(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY1
    ): CashFlowDto

    companion object {
        const val API_KEY1 = "8S0MHL3MKZWNT1OP"
        const val BASE_URL = "https://alphavantage.co"
    }

}