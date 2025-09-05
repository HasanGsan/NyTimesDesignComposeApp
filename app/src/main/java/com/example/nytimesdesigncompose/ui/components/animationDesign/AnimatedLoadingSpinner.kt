package com.example.nytimesdesigncompose.ui.components.animationDesign

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun AnimatedLoadingSpinner(
    modifier: Modifier = Modifier,
    size: Dp = 32.dp,
    color: Color = Color.White,
    segmentsCount: Int = 12
) {
    val transition = rememberInfiniteTransition(label = "spinner")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    Canvas(modifier = modifier.size(size)) {
        val centerX = this.size.width / 2
        val centerY = this.size.height / 2
        val radius = this.size.minDimension / 2
        val angleStep = 360f / segmentsCount

        for (i in 0 until segmentsCount) {
            val phase = (progress * segmentsCount - i).mod(segmentsCount.toFloat())
            val alpha = 0.2f + 0.8f * (1f - phase / segmentsCount)
            val segmentColor = color.copy(alpha = alpha.coerceIn(0f, 1f))

            val angle = (i * angleStep) * (PI / 180.0)

            val start = Offset(
                x = centerX + (radius * 0.65f) * cos(angle).toFloat(),
                y = centerY + (radius * 0.65f) * sin(angle).toFloat()
            )
            val end = Offset(
                x = centerX + (radius * 0.85f) * cos(angle).toFloat(),
                y = centerY + (radius * 0.85f) * sin(angle).toFloat()
            )

            drawLine(
                color = segmentColor,
                start = start,
                end = end,
                strokeWidth = radius * 0.20f,
                cap = StrokeCap.Round
            )
        }
    }
}
