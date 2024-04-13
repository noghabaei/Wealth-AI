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
fun CashFlow(
    netIncome: String,
    netIncomeYnYChange: String,
    isNetIncomeIncreasing: Boolean,
    cashForOperations: String,
    cashForOperationsYnYChange: String,
    isCashForOperationsIncreasing: Boolean,
    cashForInvesting: String,
    cashForInvestingYnYChange: String,
    isCashForInvestmentIncreasing: Boolean,
    cashForFinancing: String,
    cashForFinancingYnYChange: String,
    isCashForFinanceIncreasing: Boolean,
    PreviousYear: String,
    isVisible: Boolean,
    onRowClick: () -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp,13.dp,0.dp,13.dp)
                .clickable { onRowClick() },
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Text(
                text = "Cash Flow",
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
                    modifier = Modifier.weight(1.4f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Net Income
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
                ){
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
            Spacer(modifier = Modifier.height(10.dp))

            // Cash For Operations
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Cash For Operations",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = cashForOperations,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ){
                    if (isCashForOperationsIncreasing) {
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
                        text = cashForOperationsYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isCashForOperationsIncreasing) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Cash For Investing
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Cash For Investing",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = cashForInvesting,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ){
                    if (isCashForInvestmentIncreasing) {
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
                        text = cashForInvestingYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isCashForInvestmentIncreasing) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Cash For Financing
            Divider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Cash For Financing",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1.8f)
                )
                Text(
                    text = cashForFinancing,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.weight(1f)
                )
                Row(
                    modifier = Modifier.weight(1.4f),
                    Arrangement.End
                ){
                    if (isCashForFinanceIncreasing) {
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
                        text = cashForFinancingYnYChange,
                        style = MaterialTheme.typography.h3,
                        color = if (isCashForFinanceIncreasing) {
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