package com.example.wealthai.presentation.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wealthai.R
import com.example.wealthai.presentation.company_info.StockChart
import com.example.wealthai.presentation.company_info.companyviewmodel.CompanyInfoViewModel
import com.example.wealthai.ui.theme.Purple200

@Composable
fun PortfolioScreen(navController: NavController,
                    companyInfoViewModel: CompanyInfoViewModel = hiltViewModel()) {
    val state = companyInfoViewModel.companyInfoState
    LaunchedEffect(Unit){
        companyInfoViewModel.getCompanyInfo()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.portfolio),
                        style = MaterialTheme.typography.h1,
                        color = Color.White
                    )
                },
                backgroundColor = Purple200,
                contentColor = Color.White,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            StockChart(
                infos = state.stockInfos,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .align(Alignment.CenterHorizontally),
                graphColor = Color.Red
            )
        }
    }
}