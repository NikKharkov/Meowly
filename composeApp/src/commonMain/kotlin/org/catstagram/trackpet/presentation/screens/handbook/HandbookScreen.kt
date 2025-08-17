package org.catstagram.trackpet.presentation.screens.handbook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.domain.handbook.HandbookUiState
import org.catstagram.trackpet.domain.handbook.TranslatedCatData
import org.catstagram.trackpet.presentation.screens.handbook.components.CatBreedCard
import org.catstagram.trackpet.presentation.screens.handbook.components.HandbookTopBar
import org.catstagram.trackpet.presentation.shared.Status
import org.catstagram.trackpet.presentation.shared.StatusAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HandbookScreen(
    onCatClick: (Int) -> Unit,
    handbookViewModel: HandbookViewModel = koinViewModel()
) {
    val handbookUiState by handbookViewModel.handbookUiState.collectAsState()

    val translatedTexts = handbookUiState.cats.associateBy(
        { it.id },
        { breed ->
            TranslatedCatData(
                name = stringResource(breed.name),
                origin = stringResource(breed.origin),
                size = stringResource(breed.size),
                coat = stringResource(breed.coat),
                personality = breed.personality.map { stringResource(it) }
            )
        }
    )

    LaunchedEffect(handbookUiState.searchQuery, handbookUiState.showOnlySaved, translatedTexts) {
        handbookViewModel.performSearch(translatedTexts)
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        HandbookComponents(
            handbookUiState = handbookUiState,
            onCatClick = onCatClick,
            onSearchQueryChange = { query ->
                handbookViewModel.updateSearchQuery(query)
            },
            onToggleSavedFilter = { handbookViewModel.toggleSavedFilter() },
            isTablet = isTablet
        )
    }
}

@Composable
private fun HandbookComponents(
    handbookUiState: HandbookUiState,
    onCatClick: (Int) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleSavedFilter: () -> Unit,
    isTablet: Boolean
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium
    val animationSize = if (isTablet) 250.dp else 200.dp

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
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HandbookTopBar(
                searchQuery = handbookUiState.searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                showOnlySaved = handbookUiState.showOnlySaved,
                onToggleSavedFilter = onToggleSavedFilter
            )

            LazyColumn(
                modifier = Modifier
                    .widthIn(max = maxWidth)
                    .fillMaxSize(),
                contentPadding = PaddingValues(contentPadding),
                verticalArrangement = Arrangement.spacedBy(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(handbookUiState.filteredCats) { catBreed ->
                    CatBreedCard(
                        onClick = onCatClick,
                        catId = catBreed.id,
                        catImage = painterResource(catBreed.image),
                        catBreedName = stringResource(catBreed.name),
                        personality = stringResource(catBreed.personality[0]),
                        coat = stringResource(catBreed.coat),
                        size = stringResource(catBreed.size),
                        isTablet = isTablet
                    )
                }

                if (handbookUiState.filteredCats.isEmpty() &&
                    (handbookUiState.searchQuery.isNotEmpty() ||
                            (handbookUiState.showOnlySaved && handbookUiState.savedCats.isEmpty()))
                ) {
                    item {
                        StatusAnimation(
                            status = Status.EMPTY,
                            animationSize = animationSize
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HandbookScreenPreview() {
    HandbookComponents(
        handbookUiState = HandbookUiState(),
        onCatClick = {},
        onSearchQueryChange = {},
        onToggleSavedFilter = {},
        isTablet = false
    )
}