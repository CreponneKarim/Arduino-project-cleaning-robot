package com.example.test_app.components

import android.util.Log
import androidx.annotation.Px
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.test_app.data.Coords
import com.example.test_app.data.DataSpace
import com.example.test_app.ui.theme.Shapes
import com.example.test_app.ui.theme.blue1
import com.example.test_app.ui.theme.blue2
import com.example.test_app.ui.theme.white
import kotlin.math.log

@Composable
fun DrawingCanvas(
    paddingValues: PaddingValues
){
    val cardHeight      = LocalConfiguration.current.screenWidthDp.dp
    val canvasHeight    = cardHeight - paddingValues.calculateTopPadding() * 4
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(paddingValues),
        shape = Shapes.large,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ){
            Canvas(
                modifier = Modifier.height(canvasHeight)
            ){
                drawCircle(
                    color = blue2,
                    radius = canvasHeight.toPx()/2,
                    style = Stroke(width = 3.dp.toPx()),
                )
                val threshold = canvasHeight.toPx() /2.0f
                val offsetList:List<Offset> = ArrayList<Offset>().plus(
                    DataSpace.fullPreviousData.leftSensorData.coordinates.filter{
                        DataSpace.fullPreviousData.leftSensorData.distances[it.key]!!.Distance.value<threshold
                    }.map { (key,value) ->
                            Offset(value.x,value.y)
                    }
                ).plus(
                    DataSpace.fullPreviousData.middleSensorData.coordinates.filter{
                        DataSpace.fullPreviousData.middleSensorData.distances[it.key]!!.Distance.value<threshold
                    }.map { (key, value) ->
                            Offset(value.x, value.y)
                    }
                ).plus(
                    DataSpace.fullPreviousData.rightSensorData.coordinates.filter{
                        DataSpace.fullPreviousData.rightSensorData.distances[it.key]!!.Distance.value<threshold
                    }.map { (key, value) ->
                            Offset(value.x, value.y)
                    }
                )

                Log.i("slime ball", offsetList.toString())
                
                drawPoints(
                    points = offsetList,
                    pointMode = PointMode.Points,
                    brush = SolidColor(Color.Red)
                )

            }
        }
    }
}