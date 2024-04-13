package com.example.wealthai.data.repository

import com.example.wealthai.data.csv.CSVParser
import com.example.wealthai.data.local.StockDatabase
import com.example.wealthai.data.mappers.toBalanceSheet
import com.example.wealthai.data.mappers.toCashFlow
import com.example.wealthai.data.mappers.toCompanyInfo
import com.example.wealthai.data.mappers.toCompanyListing
import com.example.wealthai.data.mappers.toCompanyListingEntity
import com.example.wealthai.data.mappers.toIncomeStatement
import com.example.wealthai.data.remote.StockApi
import com.example.wealthai.domain.model.BalanceSheet
import com.example.wealthai.domain.model.CashFlow
import com.example.wealthai.domain.model.CompanyInfo
import com.example.wealthai.domain.model.CompanyListing
import com.example.wealthai.domain.model.IncomeStatement
import com.example.wealthai.domain.model.IntradayInfo
import com.example.wealthai.domain.repository.StockRepository
import com.example.wealthai.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListing()
                companyListingParser.parse(response.byteStream())
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { listing ->
                dao.clearCompanyListing()
                dao.insertCompanyListing(
                    listing.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = dao.searchCompanyListing("").map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val result = intradayInfoParser.parse(response.byteStream())
            Resource.Success(result)

        }catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }catch (e: HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load company info"
            )
        }
    }

    override suspend fun getIncomeStatementInfo(symbol: String): Resource<IncomeStatement> {
        return try {
            val result = api.getIncomeStatement(symbol)
            Resource.Success(result.toIncomeStatement())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load Income Statement info")
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Income Statement info"
            )
        }
    }

    override suspend fun getBalanceSheet(symbol: String): Resource<BalanceSheet> {
        return try {
            val result = api.getBalanceSheet(symbol)
            Resource.Success(result.toBalanceSheet())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load Balance Sheet info")
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Balance Sheet info"
            )
        }
    }

    override suspend fun getCashFlow(symbol: String): Resource<CashFlow> {
        return try {
            val result = api.getCashFlow(symbol)
            Resource.Success(result.toCashFlow())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load Cash Flow info")
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Cash Flow info"
            )
        }
    }


}