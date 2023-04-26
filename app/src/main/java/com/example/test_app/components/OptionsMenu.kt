package com.example.test_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.test_app.ui.theme.white

@Composable
fun OptionsMenu(paddingValues: PaddingValues){

    //  here maybe some options
    Card(
        modifier = Modifier
            .padding(
                top = 0.dp,
                bottom = paddingValues.calculateTopPadding(),
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
            )
            .height(300.dp)
            .fillMaxWidth(),

        backgroundColor = MaterialTheme.colors.surface
    ) {
        //  a container for all of the other shit
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ){
            Text(
                text = "Options",
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}