package com.example.wealthai.domain.repository

import com.example.wealthai.domain.model.BalanceSheet
import com.example.wealthai.domain.model.CashFlow
import com.example.wealthai.domain.model.CompanyInfo
import com.example.wealthai.domain.model.CompanyListing
import com.example.wealthai.domain.model.IncomeStatement
import com.example.wealthai.domain.model.IntradayInfo
import com.example.wealthai.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

    suspend fun getIncomeStatementInfo(
        symbol: String
    ): Resource<IncomeStatement>

    suspend fun getBalanceSheet(
        symbol: String
    ): Resource<BalanceSheet>

    suspend fun getCashFlow(
        symbol: String
    ): Resource<CashFlow>

}