package org.catstagram.trackpet.presentation.screens.handbook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.domain.handbook.HandbookUiState
import org.catstagram.trackpet.presentation.screens.handbook.components.CareTipsComponent
import org.catstagram.trackpet.presentation.screens.handbook.components.CatDetailsTopBar
import org.catstagram.trackpet.presentation.screens.handbook.components.CharacteristicsComponent
import org.catstagram.trackpet.presentation.screens.handbook.components.FunFactComponent
import org.catstagram.trackpet.presentation.screens.handbook.components.PersonalityComponent
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HandbookDetailsScreen(
    catBreedId: Int,
    onBackClick: () -> Unit,
    handbookViewModel: HandbookViewModel = koinViewModel()
) {
    val handbookUiState by handbookViewModel.handbookUiState.collectAsState()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        HandbookDetailsComponents(
            catBreedId = catBreedId,
            handbookUiState = handbookUiState,
            onBookmarkClick = { handbookViewModel.toggleBookmark(catBreedId) },
            onBackClick = onBackClick,
            isTablet = isTablet
        )
    }
}

@Composable
private fun HandbookDetailsComponents(
    catBreedId: Int,
    handbookUiState: HandbookUiState,
    onBookmarkClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    isTablet: Boolean
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium
    val catBreed = handbookUiState.cats.find { it.id == catBreedId }!!
    val isBookmarked = handbookUiState.savedCats.any { it.id == catBreedId }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            CatDetailsTopBar(
                catBreed = catBreed,
                isBookmarked = isBookmarked,
                onBookmarkClick = { onBookmarkClick(catBreedId) },
                onBackClick = onBackClick
            )

            CharacteristicsComponent(
                origin = stringResource(catBreed.origin),
                size = stringResource(catBreed.size),
                coat = stringResource(catBreed.coat),
                modifier = Modifier.padding(horizontal = contentPadding)
            )

            PersonalityComponent(
                qualities = catBreed.personality,
                modifier = Modifier.padding(horizontal = contentPadding)
            )

            CareTipsComponent(
                tip = stringResource(catBreed.careTips),
                modifier = Modifier.padding(horizontal = contentPadding)
            )

            FunFactComponent(
                funFact = stringResource(catBreed.funFact),
                modifier = Modifier.padding(horizontal = contentPadding)
            )
        }
    }
}