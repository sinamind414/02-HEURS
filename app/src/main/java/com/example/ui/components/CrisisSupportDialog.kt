package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

private const val CRISIS_988_TEL = "tel:988"
private const val NAMI_URL = "https://www.nami.org"
private const val IASP_URL = "https://www.iasp.info/suicidalthoughts/"

/**
 * The permanent "Need help now?" overlay (strategy §5 crisis rules, US-10).
 * Not a push, not an interstitial on a timer: it only ever appears when the
 * user taps the crisis entry point. The 988 action opens the dialer prefilled
 * (ACTION_DIAL — no permission needed); NAMI/IASP open in the browser. The
 * app records nothing about the tap.
 */
@Composable
fun CrisisSupportDialog(
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    fun openUrl(url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Midnight950)
                .border(1.dp, GoldBorder, RoundedCornerShape(20.dp))
                .padding(20.dp)
                .testTag("crisis_support_dialog"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Emergency,
                contentDescription = null,
                tint = GoldPrimary,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AppStrings.crisisSupportTitle(language),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = GoldLight,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AppStrings.crisisSupportBody(language),
                fontSize = 12.5.sp,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(CRISIS_988_TEL))) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("crisis_988_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Midnight950
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = AppStrings.crisis988Button(language),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { openUrl(NAMI_URL) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("crisis_nami_button"),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = AppStrings.crisisNamiButton(language),
                        fontSize = 11.5.sp,
                        maxLines = 1
                    )
                }
                OutlinedButton(
                    onClick = { openUrl(IASP_URL) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("crisis_iasp_button"),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight),
                    border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = AppStrings.crisisIaspButton(language),
                        fontSize = 11.5.sp,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = AppStrings.crisisAppNote(language),
                fontSize = 11.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = AppStrings.crisisDismiss(language),
                fontSize = 12.5.sp,
                fontWeight = FontWeight.Bold,
                color = TextSecondary,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onDismiss() }
                    .padding(horizontal = 18.dp, vertical = 6.dp)
                    .testTag("crisis_dismiss_button")
            )
        }
    }
}
