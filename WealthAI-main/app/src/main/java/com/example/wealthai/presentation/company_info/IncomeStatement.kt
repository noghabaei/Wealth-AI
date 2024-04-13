package com.example.wealthai.presentation.company_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IncomeStatement(
    revenue: String,
    revenueYnYChange: String,
    isRevenueIncreasing: Boolean,
    operatingExpense: String,
    operatingExpenseYnYChange: String,
    isOperatingExpenseIncreasing: Boolean,
    netIncome: String,
    netIncomeYnYChange: String,
    isNetIncomeIncreasing: Boolean,
    ebitda: String,
    ebitdaYnYChange: String,
    isEbitdaIncreasing: Boolean,
    PreviousYear: String,
    isVisible: Boolean,
    onRowClick: () -> Unit,

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 13.dp, 0.dp, 13.dp)
                .clickable { onRowClick() },
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(
                text = "Income Statement",
                style = MaterialTheme.typography.h2
            )
            if (isVisible) {
                Icon(
                    Icons.Outlined.ExpandLess,
                    contentDescription = "Collapse"
                )
            }else {
                Icon(
                    Icons.Outlined.ExpandMore,
                    contentDescription = "Expand",
                )
            }
        }

        if (isVisible) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {

                Text(
                    text = "(USD)",
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = PreviousYear,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Y/Y Changes",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1.4f),
                )
            }

            // Revenue
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Revenue",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = revenue,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ) {
                    if (isRevenueIncreasing) {
                        Icon(
                            Icons.Default.ArrowUpward,
                            contentDescription = "Increasing",
                            tint = Color.Green
                        )
                    } else {
                        Icon(
                            Icons.Default.ArrowDownward,
                            contentDescription = "Decreasing",
                            tint = Color.Red
                        )
                    }

                    Text(
                        text = revenueYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isRevenueIncreasing) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }

            }

            // Operating Expense
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Operating Expense",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = operatingExpense,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ) {
                    if (isOperatingExpenseIncreasing) {
                        Icon(
                            Icons.Default.ArrowUpward,
                            contentDescription = "Increasing",
                            tint = Color.Green
                        )
                    } else {
                        Icon(
                            Icons.Default.ArrowDownward,
                            contentDescription = "Decreasing",
                            tint = Color.Red
                        )
                    }

                    Text(
                        text = operatingExpenseYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isOperatingExpenseIncreasing) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }
            }

            // Net Income
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Net Income",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = netIncome,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ) {
                    if (isNetIncomeIncreasing) {
                        Icon(
                            Icons.Default.ArrowUpward,
                            contentDescription = "Increasing",
                            tint = Color.Green
                        )
                    } else {
                        Icon(
                            Icons.Default.ArrowDownward,
                            contentDescription = "Decreasing",
                            tint = Color.Red
                        )
                    }

                    Text(
                        text = netIncomeYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isNetIncomeIncreasing) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }
            }

            // EBITDA
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "EBITDA",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = ebitda,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ) {
                    if (isEbitdaIncreasing) {
                        Icon(
                            Icons.Default.ArrowUpward,
                            contentDescription = "Increasing",
                            tint = Color.Green
                        )
                    } else {
                        Icon(
                            Icons.Default.ArrowDownward,
                            contentDescription = "Decreasing",
                            tint = Color.Red
                        )
                    }

                    Text(
                        text = ebitdaYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isEbitdaIncreasing) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}