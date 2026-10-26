package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.TextGold

/**
 * Feature chip ("أقرأ الفصل", "حوار الفاتحة", station binding…) drawn as a
 * gold-outlined pill **with an enforced 44 dp minimum touch height** — the
 * Android/WCAG target minimum that plain padded `Text`s miss. The icon is
 * decorative (the label carries the meaning) and stays hidden from the
 * accessibility tree.
 *
 * [emphasized] raises the gold tint/border for the primary entry points
 * (e.g. the Fatiha dialogue chip) without changing the size system.
 */
@Composable
fun FeatureChip(
    text: String,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    emphasized: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(GoldPrimary.copy(alpha = if (emphasized) 0.14f else 0.10f))
            .border(
                1.dp,
                GoldPrimary.copy(alpha = if (emphasized) 0.65f else 0.5f),
                RoundedCornerShape(50)
            )
            .clickable(onClick = onClick)
            .heightIn(min = 44.dp)
            .padding(horizontal = 14.dp)
            .testTag(testTag)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TextGold,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = text,
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 10.5.sp,
            letterSpacing = 0.6.sp,
            color = TextGold
        )
    }
}
