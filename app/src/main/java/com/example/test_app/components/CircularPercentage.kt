package com.example.test_app.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.test_app.ui.theme.AppTheme
import com.example.test_app.ui.theme.blue2

@Composable
fun CircularPercentage(
    percentage: Float,
    max: Float=100f,
    centerText: String,
    radius:Dp=50.dp,
    strokeWidth:Dp=9.dp,
    color: Color = blue2,
    animationDurationMillis:Int = 1000,
    animationDelayMillis:Int = 1000
){
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDurationMillis,
            delayMillis = animationDelayMillis
        )
    )
    LaunchedEffect(key1 = true ){
        animationPlayed=true
    }

    AppTheme{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius * 2f)
        ) {
            Canvas(
                modifier = Modifier
                    .size(radius * 2f)
            ) {

                drawCircle(
                    color = color.copy(alpha = 0.3f),
                    radius = radius.toPx(),
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * curPercentage.value,
                    useCenter = false,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
            Text(
                text = centerText+ "\n" + (curPercentage.value * max).toInt().toString() + " % ",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
@Preview
fun PreviewCircularePercentage(){
    CircularPercentage(percentage = 0.8f, max = 100f, centerText = "Battery")
}