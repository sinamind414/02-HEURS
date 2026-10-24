package com.example.ui.components

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BismillahHeader(
    modifier: Modifier = Modifier,
    subtitle: String? = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ"
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!subtitle.isNullOrBlank()) {
            Text(
                text = subtitle,
                fontFamily = AmiriFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextGold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        IslamicGoldenDivider(modifier = Modifier.padding(top = 6.dp))
    }
}

@Composable
fun IslamicGoldenDivider(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, GoldPrimary.copy(alpha = 0.5f))
                    )
                )
        )
        Text(
            text = " ✦ ۞ ✦ ",
            fontSize = 11.sp,
            color = GoldPrimary,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(GoldPrimary.copy(alpha = 0.5f), Color.Transparent)
                    )
                )
        )
    }
}

@Composable
fun CelestialCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    borderColor: Color = GoldBorder,
    backgroundColor: Color = Midnight900.copy(alpha = 0.85f),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .shadow(elevation, RoundedCornerShape(20.dp)),
        color = backgroundColor,
        shape = RoundedCornerShape(20.dp)
    ) {
        content()
    }
}

/**
 * Animated Circular Divine Clock Dial
 * Shows the ~2 Hours (~1h 48m) total Divine Life arc and current elapsed time
 */
@Composable
fun DivineCircularClock(
    percentageLived: Float,
    elapsedDivineTimeStr: String,
    totalDivineLifeStr: String,
    language: AppLanguage = AppLanguage.FR,
    modifier: Modifier = Modifier,
    sizeDp: Dp = 230.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Box(
        modifier = modifier.size(sizeDp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 14.dp.toPx()
            val diameter = size.minDimension - strokeWidth
            val radius = diameter / 2f
            val center = Offset(size.width / 2f, size.height / 2f)

            // Background arc (full circle)
            drawCircle(
                color = Midnight700.copy(alpha = 0.6f),
                radius = radius,
                center = center,
                style = Stroke(width = strokeWidth)
            )

            // 12 hour ticks around the divine clock
            for (i in 0 until 12) {
                val angleRad = Math.toRadians((i * 30.0) - 90.0)
                val innerR = radius - (strokeWidth / 2f) - 6.dp.toPx()
                val outerR = radius - (strokeWidth / 2f) - 1.dp.toPx()
                val start = Offset(
                    center.x + (innerR * cos(angleRad)).toFloat(),
                    center.y + (innerR * sin(angleRad)).toFloat()
                )
                val end = Offset(
                    center.x + (outerR * cos(angleRad)).toFloat(),
                    center.y + (outerR * sin(angleRad)).toFloat()
                )
                drawLine(
                    color = if (i % 3 == 0) GoldPrimary.copy(alpha = 0.8f) else GoldBorder,
                    start = start,
                    end = end,
                    strokeWidth = if (i % 3 == 0) 2.5.dp.toPx() else 1.2.dp.toPx()
                )
            }

            // Progress Arc
            val sweepAngle = (percentageLived.coerceIn(0f, 100f) / 100f) * 360f
            val gradientBrush = Brush.sweepGradient(
                colors = listOf(
                    GoldPrimary,
                    CelestialCyan,
                    GoldLight,
                    GoldPrimary
                ),
                center = center
            )

            drawArc(
                brush = gradientBrush,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(diameter, diameter)
            )

            // Glowing indicator dot at current position
            if (sweepAngle > 0.5f) {
                val currentAngleRad = Math.toRadians((sweepAngle - 90f).toDouble())
                val dotX = center.x + (radius * cos(currentAngleRad)).toFloat()
                val dotY = center.y + (radius * sin(currentAngleRad)).toFloat()

                // Outer glow
                drawCircle(
                    color = GoldPrimary.copy(alpha = pulseGlow * 0.45f),
                    radius = strokeWidth * 1.1f,
                    center = Offset(dotX, dotY)
                )
                // Center point
                drawCircle(
                    color = Color.White,
                    radius = strokeWidth * 0.42f,
                    center = Offset(dotX, dotY)
                )
            }
        }

        // Inner Information Display
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = AppStrings.livedTimeLabel(language),
                fontFamily = CinzelFontFamily,
                fontSize = 10.sp,
                letterSpacing = 1.2.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = elapsedDivineTimeStr,
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = TextGold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "/ $totalDivineLifeStr",
                fontFamily = CinzelFontFamily,
                fontSize = 12.sp,
                color = TextMuted
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(GoldPrimary.copy(alpha = 0.15f))
                    .border(1.dp, GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = AppStrings.livedBadge(language, percentageLived),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    color = GoldLight
                )
            }
        }
    }
}

/**
 * Trigger subtle spiritual haptic vibration
 */
fun triggerSpiritualHaptic(context: Context, durationMs: Long = 25) {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vibratorManager?.defaultVibrator?.vibrate(
                VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE)
            )
        } else {
            @Suppress("DEPRECATION")
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            @Suppress("DEPRECATION")
            vibrator?.vibrate(durationMs)
        }
    } catch (_: Exception) {
        // Safe ignore
    }
}
