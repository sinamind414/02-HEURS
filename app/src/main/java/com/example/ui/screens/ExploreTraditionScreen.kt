package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.TraditionLayer
import com.example.ui.MainViewModel
import com.example.ui.SaatanScreen
import com.example.ui.components.triggerSpiritualHaptic
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

private data class ExploreCard(
    val title: String,
    val body: String,
    val tag: String,
    val openLabel: String? = null,
    val openTag: String? = null,
    val onOpen: () -> Unit = {}
)

/**
 * "Explore the tradition" — the progressive, always-optional hub (strategy
 * §10). Nothing is gated by time or streaks in this slice: the card list is
 * open, and the single meaningful toggle is the skin switch between the
 * light Presence Rhythm skin and the original names. The existing screens
 * (Book, Journey, Fatiha, converter) are the tradition content — the hub
 * simply reframes them and hands over.
 */
@Composable
fun ExploreTraditionScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language by viewModel.appLanguage.collectAsState()
    val tradition by viewModel.traditionLayer.collectAsState()
    val isLight = tradition.isLightSkin

    androidx.compose.material3.Surface(
        color = Midnight950,
        modifier = modifier
            .fillMaxSize()
            .testTag("explore_screen_root")
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                horizontal = 20.dp,
                vertical = 24.dp
            )
        ) {
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = AppStrings.exploreTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TextGold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = AppStrings.exploreSubtitle(language),
                        fontSize = 12.5.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            val cards = listOf(
                ExploreCard(
                    title = AppStrings.exploreCardWhyFive(language),
                    body = AppStrings.exploreCardWhyFiveBody(language),
                    tag = "explore_card_why_five"
                ),
                ExploreCard(
                    title = AppStrings.exploreCardNames(language),
                    body = AppStrings.exploreCardNamesBody(language),
                    tag = "explore_card_names"
                ),
                ExploreCard(
                    title = AppStrings.exploreCardWater(language),
                    body = AppStrings.exploreCardWaterBody(language),
                    tag = "explore_card_water"
                ),
                ExploreCard(
                    title = AppStrings.exploreCardQibla(language),
                    body = AppStrings.exploreCardQiblaBody(language),
                    tag = "explore_card_qibla"
                ),
                ExploreCard(
                    title = AppStrings.exploreCardFatiha(language),
                    body = AppStrings.exploreCardFatihaBody(language),
                    tag = "explore_card_fatiha",
                    openLabel = AppStrings.exploreOpen(language),
                    openTag = "explore_open_fatiha",
                    onOpen = { viewModel.openFatiha() }
                ),
                ExploreCard(
                    title = AppStrings.exploreCardStations(language),
                    body = AppStrings.exploreCardStationsBody(language),
                    tag = "explore_card_stations",
                    openLabel = AppStrings.exploreOpen(language),
                    openTag = "explore_open_stations",
                    onOpen = { viewModel.navigateTo(SaatanScreen.JOURNEY) }
                ),
                ExploreCard(
                    title = AppStrings.exploreCardEternal(language),
                    body = AppStrings.exploreCardEternalBody(language),
                    tag = "explore_card_eternal",
                    openLabel = AppStrings.exploreOpen(language),
                    openTag = "explore_open_eternal",
                    onOpen = { viewModel.navigateTo(SaatanScreen.CONVERTER) }
                ),
                ExploreCard(
                    title = AppStrings.exploreCardScience(language),
                    body = AppStrings.exploreCardScienceBody(language),
                    tag = "explore_card_science"
                ),
                ExploreCard(
                    title = AppStrings.exploreCardVoices(language),
                    body = AppStrings.exploreCardVoicesBody(language),
                    tag = "explore_card_voices"
                )
            )

            items(cards.size) { index ->
                val card = cards[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(Midnight800.copy(alpha = 0.9f))
                        .border(1.dp, GoldBorder, RoundedCornerShape(14.dp))
                        .padding(16.dp)
                        .testTag(card.tag)
                ) {
                    Text(
                        text = card.title,
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.5.sp,
                        color = GoldLight
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = card.body,
                        fontSize = 12.5.sp,
                        color = TextPrimary,
                        lineHeight = 18.sp
                    )
                    // Science card carries its permanent disclaimer (US-02).
                    if (card.tag == "explore_card_science") {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = AppStrings.exploreScienceDisclaimer(language),
                            fontSize = 11.sp,
                            color = TextSecondary,
                            lineHeight = 15.sp
                        )
                    }
                    if (card.openLabel != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = {
                                triggerSpiritualHaptic(context)
                                card.onOpen()
                            },
                            modifier = Modifier.testTag(card.openTag ?: card.tag + "_open"),
                            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
                                contentColor = GoldLight
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text = card.openLabel, fontSize = 12.sp)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        triggerSpiritualHaptic(context)
                        viewModel.setTraditionLayer(
                            if (isLight) TraditionLayer.GRADUAL else TraditionLayer.OFF
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("explore_skin_toggle"),
                    colors = if (isLight) {
                        ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = Midnight950
                        )
                    } else {
                        ButtonDefaults.buttonColors(
                            containerColor = Midnight800,
                            contentColor = GoldLight
                        )
                    },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (isLight) {
                            AppStrings.exploreUnlockBtn(language)
                        } else {
                            AppStrings.exploreKeepSimpleBtn(language)
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.5.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (!isLight) {
                    Text(
                        text = AppStrings.exploreUnlockedNote(language),
                        fontSize = 11.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
