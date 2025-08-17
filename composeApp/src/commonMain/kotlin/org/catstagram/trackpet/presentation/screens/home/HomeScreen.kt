package org.catstagram.trackpet.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.utils.now
import org.catstagram.trackpet.ads.AdBanner
import org.catstagram.trackpet.presentation.screens.add_edit.CatViewModel
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.domain.news.News
import org.catstagram.trackpet.presentation.screens.home.components.CatCard
import org.catstagram.trackpet.presentation.screens.home.components.NewsButton
import org.catstagram.trackpet.presentation.screens.news.NewsViewModel
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    toGalleryScreen: (Long) -> Unit,
    toNewsScreen: () -> Unit,
    onEditClick: (Long) -> Unit,
    catViewModel: CatViewModel = koinViewModel(),
    newsViewModel: NewsViewModel = koinViewModel()
) {
    val catState by catViewModel.catState.collectAsState()
    val newsState by newsViewModel.newsUiState.collectAsState()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        HomeScreenComponents(
            isTablet = isTablet,
            cats = catState.cats,
            news = newsState.news,
            saveImage = { catId, avatarImage ->
                catViewModel.saveProfilePicture(catId, avatarImage)
            },
            toGalleryScreen = toGalleryScreen,
            toNewsScreen = toNewsScreen,
            onEditClick = onEditClick
        )
    }
}

@Composable
private fun HomeScreenComponents(
    isTablet: Boolean,
    cats: List<UserCat>,
    news: List<News>,
    toGalleryScreen: (Long) -> Unit,
    toNewsScreen: () -> Unit,
    saveImage: (Long, ByteArray?) -> Unit,
    onEditClick: (Long) -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .fillMaxHeight()
                .windowInsetsPadding(WindowInsets.statusBars),
            contentPadding = PaddingValues(contentPadding),
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            items(items = cats, key = { it.id }) { cat ->
                CatCard(
                    saveImage = { _, uri ->
                        saveImage(cat.id, uri)
                    },
                    cat = cat,
                    toGalleryScreen = { catId ->
                        toGalleryScreen(catId)
                    },
                    onEditClick = onEditClick
                )
            }

            item {
                NewsButton(
                    newsList = news.filter { it.createdAt == LocalDate.now().toString() },
                    onViewAllNewsClick = toNewsScreen
                )
            }

            item {
                AdBanner(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenComponents(
        isTablet = false,
        cats = emptyList(),
        news = emptyList(),
        toGalleryScreen = {},
        toNewsScreen = {},
        saveImage = { _, _ -> },
        onEditClick = {}
    )
}