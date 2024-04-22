package com.example.wealthai.domain.model

import java.time.LocalDate

data class DailyInfo(
    val date: LocalDate,
    val close: Double
)
