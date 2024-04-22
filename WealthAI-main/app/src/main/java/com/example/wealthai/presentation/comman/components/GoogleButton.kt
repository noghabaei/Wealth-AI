package com.example.wealthai.presentation.comman.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.wealthai.R

@Composable
fun GoogleButton(
    onClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
        Button(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(5.dp)
                )
            ,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(10.dp)
        ) {
            Row (verticalAlignment = Alignment.CenterVertically ){
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_google_logo
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = stringResource(id = R.string.google_name),
                    style = TextStyle( color = Color.Gray, fontSize = 17.sp, fontWeight = FontWeight.Bold),
                )
            }

        }
    }
}