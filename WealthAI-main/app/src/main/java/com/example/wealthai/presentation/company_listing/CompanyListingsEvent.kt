package com.example.wealthai.presentation.company_listing

sealed class CompanyListingsEvent {
    object Refresh: CompanyListingsEvent()
    data class OnSearchQueryClick(val query: String): CompanyListingsEvent()
}
