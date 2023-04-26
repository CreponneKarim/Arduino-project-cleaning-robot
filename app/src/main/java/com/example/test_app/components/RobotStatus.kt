package com.example.test_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.test_app.data.DataSpace
import com.example.test_app.ui.theme.*
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RobotStatusCard(
    paddingValues:PaddingValues,
    radius: Dp = 40.dp
){
    val badwater by remember {
        DataSpace.fullPreviousData.vehicle.badWaterLevel
    }
    val cleanWater by remember {
        DataSpace.fullPreviousData.vehicle.cleanWaterLevel
    }
    val battery by remember {
        DataSpace.fullPreviousData.vehicle.batteryLevel
    }

    Card(
        modifier = Modifier
            .padding(
                top = 0.dp,
                bottom = paddingValues.calculateTopPadding(),
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr)
            )
            .height(radius * 3),
        shape = Shapes.large,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(radius * 3)
                .padding(
                    PaddingValues(10.dp)
                ),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //  Battery
            CircularPercentage(
                percentage = battery,
                centerText = "Battery",
                color = green1,
                radius = radius
            )
            //  Clean water
            CircularPercentage(
                percentage = cleanWater,
                centerText = "Clean\nwater",
                color = blue2,
                radius = radius
            )
            //  Bad water
            CircularPercentage(
                percentage = badwater,
                centerText = "Bad\nwater",
                color = brown1,
                radius = radius
            )
        }
    }

}

@Composable
@Preview
fun PreviewRobotStatusCard(){
    RobotStatusCard(
        paddingValues = PaddingValues(
            horizontal = 5.dp,
            vertical = 5.dp
        )
    )
}