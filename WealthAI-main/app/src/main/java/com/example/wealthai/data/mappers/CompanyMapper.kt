package com.example.wealthai.data.mappers

import com.example.wealthai.data.local.CompanyListingEntity
import com.example.wealthai.data.remote.dto.BalanceSheetDto
import com.example.wealthai.data.remote.dto.BalanceSheetReportDto
import com.example.wealthai.data.remote.dto.CashFlowDto
import com.example.wealthai.data.remote.dto.CashFlowReportDto
import com.example.wealthai.data.remote.dto.CompanyInfoDto
import com.example.wealthai.data.remote.dto.IncomeStatementDto
import com.example.wealthai.data.remote.dto.IncomeStatementReportDto
import com.example.wealthai.domain.model.BalanceSheet
import com.example.wealthai.domain.model.BalanceSheetReport
import com.example.wealthai.domain.model.CashFlow
import com.example.wealthai.domain.model.CashFlowReport
import com.example.wealthai.domain.model.CompanyInfo
import com.example.wealthai.domain.model.CompanyListing
import com.example.wealthai.domain.model.IncomeStatement
import com.example.wealthai.domain.model.IncomeStatementReport

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol?: "",
        description = description?: "",
        name = name?: "",
        country = country?: "",
        industry = industry?: "",
        marketCap = marketCap?: "",
        peRatio = peRatio?: "",
        dividendYield = dividendYield?: "",
        primaryExchange = exchange?: ""
    )
}

fun IncomeStatementReportDto.toIncomeStatementReport(): IncomeStatementReport {
    return IncomeStatementReport(
        revenue = revenue?: "",
        operatingExpense = operatingExpenses?: "",
        netIncome = netIncome?: "",
        editda = editda?: "",
        date = date?: ""
    )
}

fun IncomeStatementDto.toIncomeStatement(): IncomeStatement {
    return IncomeStatement(
        annualReports = annualReports.orEmpty().mapNotNull { it.toIncomeStatementReport() },
        quarterlyReports = quarterlyReports.orEmpty().mapNotNull { it.toIncomeStatementReport() },
        symbol = symbol?: ""
    )
}

fun BalanceSheetReportDto.toBalanceSheetReport(): BalanceSheetReport {
    return BalanceSheetReport(
        cashAndShortTermInvestments = cashAndShortTermInvestments?: "",
        totalAssets = totalAssets?: "",
        totalLiabilities = totalLiabilities?: "",
        totalEquity = totalEquity?: "",
        shareOutstanding = shareOutstanding?: "",
        date = date?: ""
    )
}

fun BalanceSheetDto.toBalanceSheet(): BalanceSheet {
    return BalanceSheet(
        annualReport = annualReports.orEmpty().mapNotNull { it.toBalanceSheetReport() },
        quarterReport = quarterlyReports.orEmpty().mapNotNull { it.toBalanceSheetReport() },
        symbol = symbol?: ""
    )
}

fun CashFlowReportDto.toCashFlowReport(): CashFlowReport {
    return CashFlowReport(
        netIncome = netIncome?: "",
        cashForOperations = cashForOperations?: "",
        cashForInvesting = cashForInvesting?: "",
        cashForFinancing = cashForFinancing?: "",
        date = date?: ""
    )
}

fun CashFlowDto.toCashFlow(): CashFlow {
    return CashFlow(
        annualReport = annualReports.orEmpty().mapNotNull { it.toCashFlowReport() },
        quarterReport = quarterlyReports.orEmpty().mapNotNull { it.toCashFlowReport() },
        symbol = symbol?: ""
    )
}