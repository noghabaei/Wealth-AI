package com.example.wealthai.di

import com.example.wealthai.data.csv.CSVParser
import com.example.wealthai.data.csv.CompanyListingParser
import com.example.wealthai.data.csv.DailyInfoParser
import com.example.wealthai.data.csv.IntradayInfoParser
import com.example.wealthai.data.repository.StockRepositoryImpl
import com.example.wealthai.domain.model.CompanyListing
import com.example.wealthai.domain.model.DailyInfo
import com.example.wealthai.domain.model.IntradayInfo
import com.example.wealthai.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindDailyInfoParser(
        dailyInfoParser: DailyInfoParser
    ): CSVParser<DailyInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}