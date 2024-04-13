//package com.example.infotrade.presentation.company_info.components
//
//import android.view.MotionEvent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInteropFilter
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.dp
//import androidx.core.graphics.component1
//import com.example.infotrade.domain.model.IncomeStatement
//import com.example.infotrade.domain.model.IncomeStatementReport
//import java.lang.Math.abs
//
//@Composable
//fun ChartBar(
//    modifier: Modifier = Modifier,
//    percentage: Int,
////    brush: Brush,
//    isHighlighted: Boolean = false
//) {
//    Box(
//        modifier = modifier
//            .clip(RoundedCornerShape(30.dp))
//            .height(abs(percentage).dp)
//            .width(3.dp)
//            .background(color = if (!isHighlighted) Color.Black.copy(alpha = 0.5f) else Color.Transparent)
//    )
//}
//
//@Composable
//private fun GroupLabel(
//    text: String,
//    color: Color = Color.Green,
//    highlightColor: Color = Color.LightGray,
//    textStyle: TextStyle = MaterialTheme.typography.subtitle1,
//    isHighlighted: Boolean = false
//) {
//    Text(
//        modifier = Modifier.padding(bottom = 8.dp),
//        text = text,
//        color = if (isHighlighted) highlightColor else color,
//        style = textStyle
//    )
//}
//
//@OptIn(ExperimentalComposeUiApi::class)
//@Composable
//fun ChartBarGroup(
//    modifier: Modifier = Modifier,
//    label: String,
//    values: Float,
//    onGroupSelected: () -> Unit = {},
//    onRemoveSelection: () -> Unit = {},
//    isSelected: Boolean,
//    isNothingSelected: Boolean
//) {
//    Column(
//        modifier = modifier
//            .height(30.dp)
//            .pointerInteropFilter { event ->
//                when (event.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        onGroupSelected()
//                    }
//                    MotionEvent.ACTION_UP -> {
//                        onRemoveSelection()
//                    }
//                    MotionEvent.ACTION_CANCEL -> {
//                        onRemoveSelection()
//                    }
//                }
//                true
//            },
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        GroupLabel(
//            text = label,
//            isHighlighted = isSelected
//        )
//        Spacer(modifier = Modifier.weight(1f))
//
//        Row(
//            modifier = Modifier.height(30.dp), verticalAlignment = Alignment.Bottom
//        ) {
//            values.forEachIndexed { index, item ->
//                val (realPercentage) = item
//                val yOffset: Int
//                val percentage = realPercentage.coerceIn(-15 + 1, 100 - 1)
//
//                yOffset = if (percentage >= 0) {
//                    abs(100)
//                } else if (percentage in 15..-1) {
//                    abs(15) + percentage
//                } else {
//                    0
//                }
//                Column(
//                    modifier = Modifier.fillMaxHeight(),
//                    verticalArrangement = Arrangement.Bottom
//                ) {
//                    ChartBar(
//                        percentage = percentage,
////                        brush = Brush.verticalGradient(listOf(color, color)),
////                        isHighlighted = isSelected || isNothingSelected
//                    )
//                    Spacer(modifier = Modifier.height(yOffset.dp))
//                }
//                if (index in 0 until values.size - 1) {
//                    Spacer(modifier = Modifier.width(1.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun BarGraph(
//    barGroups: List<BarChartData>,
//    onGroupSelectionChanged: (index: Int) -> Unit = {}
//) {
//    val backgroundBrush = Brush.verticalGradient(
//        listOf(Color.White.copy(alpha = 0.10f), Color.White.copy(alpha = 0.03f))
//    )
//
//    val selectedGroupIndex = remember {
//        mutableStateOf(-1)
//    }
//
//    Row(
//        modifier = Modifier
//            .clip(RoundedCornerShape(5.dp))
//            .fillMaxWidth()
//            .background(backgroundBrush)
//            .padding(8.dp)
//    ) {
//        barGroups.forEachIndexed { index, item ->
//            if (index == 0) {
//                Spacer(modifier = Modifier.weight(1f))
//            }
//            ChartBarGroup(
//                label = item.label,
//                values = item.netIncome,
//                onGroupSelected = {
//                    selectedGroupIndex.value = index
//                    onGroupSelectionChanged(selectedGroupIndex.value)
//                },
//                onRemoveSelection = {
//                    selectedGroupIndex.value = -1
//                    onGroupSelectionChanged(selectedGroupIndex.value)
//                },
//                isSelected = selectedGroupIndex.value == index,
//                isNothingSelected = selectedGroupIndex.value == -1
//            )
//            Spacer(modifier = Modifier.weight(1f))
//        }
//    }
//}
//
//
//
