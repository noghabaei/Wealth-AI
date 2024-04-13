package com.example.wealthai.presentation.company_info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StockHighlights(
    previousClose: String,
    dayRange: String,
    marketCap: String,
    peRatio: String,
    dividendYield: String,
    primaryExchange: String
) {

    Column(modifier = Modifier
        .fillMaxSize()
    ) {

        // Previous Close
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "PREVIOUS CLOSE",
                style = MaterialTheme.typography.h4
            )
            Text(
                text = previousClose,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        // Day Range
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "DAY RANGE",
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = dayRange,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        // Market Cap
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "MARKET CAP",
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = marketCap,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        // P/E Ratio
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "P/E RATIO",
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = peRatio,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        // Dividend yield
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "DIVIDEND YIELD",
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = dividendYield,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        // Primary Exchange
        Divider(
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                text = "PRIMARY EXCHANGE",
                style = MaterialTheme.typography.h4,
            )
            Text(
                text = primaryExchange,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

    }



}