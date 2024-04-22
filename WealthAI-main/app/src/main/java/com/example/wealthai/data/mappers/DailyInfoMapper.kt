package com.example.wealthai.data.mappers

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.example.wealthai.data.remote.dto.DailyInfoDto
import com.example.wealthai.domain.model.DailyInfo
import java.time.LocalDate

fun DailyInfoDto.toDailyInfo(): DailyInfo {
    val pattern = "yyyy-MM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDate = LocalDate.parse(timestamp, formatter)

    return DailyInfo(
        date = localDate,
        close = close
    )
}