package org.catstagram.trackpet.presentation.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.ads.NativeAd
import org.catstagram.trackpet.domain.news.News
import org.catstagram.trackpet.domain.news.NewsUiState
import org.catstagram.trackpet.presentation.screens.news.components.NewsCard
import org.catstagram.trackpet.presentation.shared.TopBar
import org.catstagram.trackpet.presentation.screens.settings.SettingsViewModel
import org.catstagram.trackpet.presentation.shared.Status
import org.catstagram.trackpet.presentation.shared.StatusAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewsScreen(
    onNewsClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    toSettingsScreen: () -> Unit,
    settingsViewModel: SettingsViewModel,
    newsViewModel: NewsViewModel = koinViewModel()
) {
    val newsUiState by newsViewModel.newsUiState.collectAsState()
    val settingsUiState by settingsViewModel.settingsUiState.collectAsState()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        NewsScreenComponents(
            newsUiState = newsUiState,
            onNewsClick = onNewsClick,
            onBackClick = onBackClick,
            toSettingsScreen = toSettingsScreen,
            userAvatarURL = settingsUiState.user?.profilePicture,
            isTablet = isTablet
        )
    }
}

@Composable
private fun NewsScreenComponents(
    newsUiState: NewsUiState,
    onNewsClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    toSettingsScreen: () -> Unit,
    userAvatarURL: String?,
    isTablet: Boolean
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium
    val animationSize = if (isTablet) 250.dp else 200.dp

    Scaffold(
        topBar = {
            TopBar(
                userAvatarURL = userAvatarURL,
                onBackClick = onBackClick,
                toSettingsScreen = toSettingsScreen
            )
        },
        containerColor = colors.background,
        contentWindowInsets = WindowInsets.systemBars
    ) { paddingValues ->
        when {
            newsUiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    StatusAnimation(
                        status = Status.LOADING,
                        animationSize = animationSize
                    )
                }
            }

            newsUiState.isError -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    StatusAnimation(
                        status = Status.ERROR,
                        animationSize = animationSize
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .widthIn(max = maxWidth)
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = contentPadding),
                    contentPadding = PaddingValues(vertical = contentPadding),
                    verticalArrangement = Arrangement.spacedBy(contentPadding)
                ) {
                    newsUiState.news.forEachIndexed { index, news ->
                        item {
                            NewsCard(
                                news = news,
                                onNewsClick = onNewsClick,
                                isTablet = isTablet
                            )
                        }

                        if ((index + 1) % 3 == 0) {
                            item {
                                NativeAd()
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun NewsScreenPreview() {
    NewsScreenComponents(
        newsUiState = NewsUiState(
            news = listOf(
                News(
                    id = 1,
                    title = "Scientists Discover That Cats Actually Have Wi-Fi Built Into Their Whiskers",
                    content = "A groundbreaking study from the University of Technology has revealed that domestic cats possess natural wireless connectivity through their whiskers. The research team, led by Dr. Sarah Mitchell, found that cats can detect and connect to nearby Wi-Fi networks using microscopic sensors in their facial hair. 'We always wondered why cats seem to know when we're working from home,' said Dr. Mitchell. 'Now we know they're literally connected to our internet.' The discovery explains why cats often sit on keyboards and laptops. Pet owners are advised to change their Wi-Fi passwords regularly to prevent unauthorized feline access.",
                    imageUrl = "https://example.com/images/cat-wifi-whiskers.jpg",
                    createdAt = "2025-07-15"
                ),
                News(
                    id = 2,
                    title = "Local Man Breaks World Record by Eating 47 Pizzas in Single Day",
                    content = "Tommy Rodriguez from Chicago has officially broken the Guinness World Record for pizza consumption in a 24-hour period. The 28-year-old software developer consumed 47 medium-sized pizzas of various toppings, surpassing the previous record of 34 pizzas set in 2019. 'I've been training for this moment my entire life,' Rodriguez stated between bites of his victory slice. The attempt was supervised by official Guinness judges and medical professionals. Rodriguez's secret? 'I alternate between pepperoni and Hawaiian to keep my taste buds interested.' He plans to celebrate his achievement by taking a month-long break from pizza, though he admits this might be optimistic.",
                    imageUrl = "https://example.com/images/pizza-record-holder.jpg",
                    createdAt = "2025-07-14"
                ),
                News(
                    id = 3,
                    title = "New Study Reveals That Houseplants Have Been Secretly Judging Our Life Choices",
                    content = "Researchers at the Institute of Botanical Psychology have published a controversial study suggesting that houseplants are capable of forming opinions about their owners' lifestyle decisions. Using advanced leaf-reading technology, scientists monitored the electromagnetic responses of common household plants like pothos and snake plants. The results were startling: plants showed increased stress levels when owners made poor financial decisions, forgot to water them, or binge-watched reality TV for extended periods. 'My fiddle leaf fig has been giving me the cold shoulder ever since I ordered takeout five nights in a row,' reported study participant Jennifer Adams. The research team recommends talking to plants about major life decisions before making them.",
                    imageUrl = "https://example.com/images/judgmental-plants.jpg",
                    createdAt = "2025-07-13"
                )
            )
        ),
        onNewsClick = {},
        onBackClick = {},
        toSettingsScreen = {},
        userAvatarURL = null,
        isTablet = false
    )
}