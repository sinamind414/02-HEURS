package com.example.ui.screens

import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import com.example.R
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.TextGold
import kotlinx.coroutines.delay

/**
 * The opening "Prayer of Taste" logo splash.
 *
 * The designarena calligraphy video (a sujud figure woven from Arabic
 * lettering) plays once on every cold open; one tap anywhere — or the video's
 * end — dissolves into the app. The video is shown at its native aspect
 * (768 × 992) fit to the width: the static poster (extracted from the very
 * last frame) fills the rest of the screen behind it, so the letterboxing
 * reads as one continuous artwork rather than black bars. Every playback
 * failure falls through to the app; a hard 12 s watchman catches a hung
 * decoder.
 */
@Composable
fun LogoSplashOverlay(
    language: AppLanguage,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF113032), Color(0xFF0C2629), Color(0xFF03151C))
                )
            )
            .testTag("logo_splash")
    ) {
        // Poster: the same artwork as the video's final frame, at the exact
        // geometry of the video above it — it only shows during buffering;
        // afterwards the video covers it pixel for pixel. The screen letterbox
        // areas fall on the sampled teal gradient, matching the video edges.
        Image(
            painter = painterResource(R.drawable.saatan_logo_poster),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .aspectRatio(768f / 992f)
        )

        // The video itself — native ratio, full width, centered.
        AndroidView(
            factory = { context ->
                VideoView(context).apply {
                    setVideoURI(
                        ("android.resource://${context.packageName}/${R.raw.saatan_logo}").toUri()
                    )
                    setOnPreparedListener { player ->
                        player.setVolume(0f, 0f) // generated audio trails: silent
                        player.isLooping = false
                        start()
                    }
                    setOnCompletionListener { onDismiss() }
                    setOnErrorListener { _, _, _ ->
                        onDismiss()
                        true
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .aspectRatio(768f / 992f)
                .testTag("logo_splash_video")
        )

        // Any tap enters the app (above both poster and video).
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) { detectTapGestures { onDismiss() } }
        )

        // Subtle skip hint, above the gesture catcher.
        Text(
            text = AppStrings.splashEnterHint(language),
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = 1.2.sp,
            textAlign = TextAlign.Center,
            color = TextGold.copy(alpha = 0.8f),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(bottom = 26.dp)
                .testTag("logo_splash_hint")
        )

        // Watchman: whatever happens (stalled decoder, cold-start freeze on a
        // slow device), the app always wins after this.
        LaunchedEffect(Unit) {
            delay(12_000L)
            onDismiss()
        }
    }
}
