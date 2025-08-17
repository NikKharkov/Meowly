package org.catstagram.trackpet.presentation.screens.gallery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_add
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import org.catstagram.trackpet.data.gallery.rememberShareManager
import org.catstagram.trackpet.domain.cat.CatUiState
import org.catstagram.trackpet.domain.gallery.GalleryState
import org.catstagram.trackpet.managers.vibration.VibrationManager
import org.catstagram.trackpet.presentation.screens.add_edit.CatViewModel
import org.catstagram.trackpet.presentation.screens.gallery.components.AuthRequiredBanner
import org.catstagram.trackpet.presentation.screens.gallery.components.GalleryBottomActionBar
import org.catstagram.trackpet.presentation.screens.gallery.components.GalleryImageItem
import org.catstagram.trackpet.presentation.screens.gallery.components.GalleryTopBar
import org.catstagram.trackpet.presentation.screens.gallery.components.SelectionTopBar
import org.catstagram.trackpet.presentation.shared.Status
import org.catstagram.trackpet.presentation.shared.StatusAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GalleryScreen(
    catId: Long,
    onBackClick: () -> Unit,
    toSettingsScreen: () -> Unit,
    galleryViewModel: GalleryViewModel = koinViewModel(),
    catViewModel: CatViewModel = koinViewModel()
) {
    val galleryState by galleryViewModel.galleryState.collectAsState()
    val catState by catViewModel.catState.collectAsState()

    var selectedImages by remember { mutableStateOf(setOf<String>()) }
    var isSelectionMode by remember { mutableStateOf(false) }
    val shareManager = rememberShareManager()

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        GalleryComponents(
            catId = catId,
            galleryState = galleryState,
            catState = catState,
            toSettingsScreen = toSettingsScreen,
            onSaveImages = { byteArrays -> galleryViewModel.saveImages(byteArrays, catId) },
            onLoadImages = { galleryViewModel.loadImages(catId) },
            onBackClick = onBackClick,
            isTablet = isTablet,
            selectedImages = selectedImages,
            isSelectionMode = isSelectionMode,
            onImageLongPress = { imageUrl ->
                selectedImages = setOf(imageUrl)
                isSelectionMode = true
                VibrationManager.vibrate(100)
            },
            onImageClick = { imageUrl ->
                if (isSelectionMode) {
                    if (imageUrl in selectedImages) {
                        selectedImages = selectedImages - imageUrl
                    } else {
                        selectedImages = selectedImages + imageUrl
                        VibrationManager.vibrate(100)
                    }

                    if (selectedImages.isEmpty()) {
                        isSelectionMode = false
                    }
                }
            },
            onDeleteSelected = {
                galleryViewModel.deleteImages(imageUrls = selectedImages.toList(),catId = catId)
                selectedImages = emptySet()
                isSelectionMode = false
            },
            onClearSelection = {
                selectedImages = emptySet()
                isSelectionMode = false
            },
            onSharePhoto = {
                galleryViewModel.shareImages(selectedImages.toList(), shareManager)
            }
        )
    }
}

@Composable
private fun GalleryComponents(
    catId: Long,
    galleryState: GalleryState<Map<Long, List<String>>>,
    catState: CatUiState,
    toSettingsScreen: () -> Unit,
    onSaveImages: (List<ByteArray>) -> Unit,
    onLoadImages: () -> Unit,
    onBackClick: () -> Unit,
    isTablet: Boolean,
    selectedImages: Set<String>,
    isSelectionMode: Boolean,
    onImageLongPress: (String) -> Unit,
    onImageClick: (String) -> Unit,
    onDeleteSelected: () -> Unit,
    onClearSelection: () -> Unit,
    onSharePhoto: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val animationSize = if (isTablet) 250.dp else 200.dp
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium
    val scope = rememberCoroutineScope()

    val currentImages = galleryState.data?.get(catId) ?: emptyList()
    val remainingSlots = 10 - currentImages.size

    val selectionMode = when {
        remainingSlots <= 0 -> SelectionMode.Single
        remainingSlots == 1 -> SelectionMode.Single
        else -> SelectionMode.Multiple(remainingSlots)
    }

    val imagesPicker = rememberImagePickerLauncher(
        selectionMode = selectionMode,
        scope = scope,
        onResult = { byteArrays ->
            if (byteArrays.isNotEmpty() && remainingSlots > 0) {
                onSaveImages(byteArrays)
            }
        }
    )

    LaunchedEffect(Unit) {
        onLoadImages()
    }

    val shouldShowFab = galleryState.error == null &&
            remainingSlots > 0 &&
            !galleryState.isLoading &&
            !galleryState.isNotAuthenticated &&
            !isSelectionMode

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .windowInsetsPadding(WindowInsets.navigationBars),
        topBar = {
            if (isSelectionMode) {
                SelectionTopBar(
                    selectedCount = selectedImages.size,
                    onClear = onClearSelection
                )
            } else {
                GalleryTopBar(
                    onBackClick = onBackClick,
                    catAvatar = catState.cats.find { it.id == catId }?.avatarImg
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = isSelectionMode,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                GalleryBottomActionBar(
                    onDeletePhoto = onDeleteSelected,
                    onSharePhoto = onSharePhoto
                )
            }
        },
        floatingActionButton = {
            if (shouldShowFab) {
                Image(
                    painter = painterResource(Res.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .pressAnimation(onClick = { imagesPicker.launch() })
                )
            }
        },
        containerColor = colors.background
    ) { paddingValues ->

        when {
            galleryState.isLoading -> {
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

            galleryState.error != null -> {
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

            galleryState.isNotAuthenticated -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AuthRequiredBanner(
                        animationSize = animationSize,
                        toSettingsScreen = toSettingsScreen
                    )
                }
            }

            galleryState.data != null -> {
                val images = galleryState.data[catId] ?: emptyList()

                if (images.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        StatusAnimation(
                            status = Status.EMPTY,
                            animationSize = animationSize
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(if (isTablet) 3 else 2),
                        contentPadding = PaddingValues(contentPadding),
                        modifier = Modifier
                            .widthIn(max = maxWidth)
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        itemsIndexed(images) { index, imageUrl ->
                            val isSelected = imageUrl in selectedImages

                            GalleryImageItem(
                                imageUrl = imageUrl,
                                isSelected = isSelected,
                                contentPadding = contentPadding,
                                onImageLongPress = onImageLongPress,
                                onImageClick = onImageClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun GalleryComponentsPreview() {
    GalleryComponents(
        catId = 0L,
        galleryState = GalleryState(
            data = null,
            error = null,
            isLoading = false
        ),
        toSettingsScreen = {},
        catState = CatUiState(),
        onSaveImages = {},
        onLoadImages = {},
        onBackClick = {},
        onImageClick = {},
        onClearSelection = {},
        onDeleteSelected = {},
        onImageLongPress = {},
        isSelectionMode = false,
        selectedImages = emptySet(),
        onSharePhoto = {},
        isTablet = false
    )
}