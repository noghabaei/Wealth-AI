package com.example.wealthai.data.csv

import com.example.wealthai.data.mappers.toDailyInfo
import com.example.wealthai.data.remote.dto.DailyInfoDto
import com.example.wealthai.domain.model.DailyInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DailyInfoParser @Inject constructor(): CSVParser<DailyInfo> {

    override suspend fun parse(stream: InputStream): List<DailyInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0)?: return@mapNotNull null
                    val close = line.getOrNull(4)?: return@mapNotNull null
                    val dto = DailyInfoDto(timestamp, close.toDouble())
                    dto.toDailyInfo()
                }
                .sortedBy {
                    it.date
                }
                .also {
                    csvReader.close()
                }
        }
    }
}