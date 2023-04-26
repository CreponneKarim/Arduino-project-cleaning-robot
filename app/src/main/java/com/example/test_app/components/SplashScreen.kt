package com.example.test_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.test_app.ui.theme.AppTheme
import com.example.test_app.ui.theme.blue2

@Composable

fun SplashScreen(){
    AppTheme() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),

        ) {contentPadding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.primaryVariant),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Logo(contentPadding = contentPadding)
            }
        }
    }

}

@Composable
fun Logo(
    contentPadding: PaddingValues
){
    Text(
        modifier = Modifier
            .padding(contentPadding),
        text = "LOGO",
        color = Color(0xFFFFFFFF),
        fontSize = 100.sp
    )

}

@Composable
@Preview
fun PreviewSpalshScreen(){
    SplashScreen()
}
