package org.catstagram.trackpet.presentation.screens.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.btn_continue
import catstagrammp.composeapp.generated.resources.btn_edit
import kotlinx.datetime.LocalDate
import org.catstagram.trackpet.domain.cat.CatUiState
import org.catstagram.trackpet.domain.cat.local.CatsGender
import org.catstagram.trackpet.presentation.screens.add_edit.components.BirthDateField
import org.catstagram.trackpet.presentation.screens.add_edit.components.GenderSelectionField
import org.catstagram.trackpet.presentation.screens.add_edit.components.NameInputField
import org.catstagram.trackpet.presentation.screens.add_edit.components.WelcomeHeader
import org.catstagram.trackpet.presentation.shared.CustomGradientButton
import org.catstagram.trackpet.presentation.shared.LottieAnimation
import org.catstagram.trackpet.presentation.shared.Status
import org.catstagram.trackpet.presentation.shared.StatusAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddEditScreen(
    onContinue: () -> Unit,
    isFirstLaunch: Boolean,
    catId: Long? = null,
    catViewModel: CatViewModel = koinViewModel()
) {
    val catState by catViewModel.catState.collectAsState()

    val isFormValid = catState.name.isNotEmpty() &&
            catState.gender != null &&
            !catState.isLoading &&
            catState.error == null &&
            !catState.isNameError &&
            !catState.isGenderError

    LaunchedEffect(Unit) {
        catViewModel.clearErrors()
        if (catId == null) {
            catViewModel.resetState()
        }
    }

    LaunchedEffect(catId) {
        catId?.let { id ->
            catViewModel.startEditing(id)
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        AddEditComponents(
            catState = catState,
            onSetName = catViewModel::setName,
            onSetBirthDate = catViewModel::setBirthDate,
            onSetGender = catViewModel::setGender,
            onSaveCat = {
                catViewModel.saveCat { onContinue() }
            },
            isFormValid = isFormValid,
            isTablet = isTablet,
            isEditing = catState.isEditing,
            isFirstLaunch = isFirstLaunch
        )
    }
}

@Composable
private fun AddEditComponents(
    catState: CatUiState,
    onSetName: (String) -> Unit,
    onSetBirthDate: (LocalDate) -> Unit,
    onSetGender: (CatsGender?) -> Unit,
    onSaveCat: () -> Unit,
    isFormValid: Boolean,
    isTablet: Boolean,
    isEditing: Boolean,
    isFirstLaunch: Boolean
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val animationSize = if (isTablet) 250.dp else 200.dp
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium

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
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            if (isFirstLaunch) {
                WelcomeHeader(animationSize = animationSize)
            } else {
                LottieAnimation(
                    fileName = "cat_welcoming.lottie",
                    modifier = Modifier
                        .size(animationSize)
                        .padding(contentPadding)
                )
            }

            NameInputField(
                value = catState.name,
                onValueChange = onSetName,
            )

            GenderSelectionField(
                selectedGender = catState.gender,
                onGenderChange = onSetGender
            )

            BirthDateField(
                birthDate = catState.birthDate,
                onSetBirthDate = onSetBirthDate
            )

            CustomGradientButton(
                onClick = {
                    onSaveCat()
                },
                enabled = isFormValid,
                text = if (isEditing) stringResource(Res.string.btn_edit) else stringResource(Res.string.btn_continue)
            )

            if (catState.isLoading) {
                StatusAnimation(
                    status = Status.LOADING,
                    animationSize = animationSize
                )
            }

            catState.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red
                )
            }
        }
    }
}

@Preview()
@Composable
private fun AddEditScreenPreview() {
    AddEditComponents(
        catState = CatUiState(),
        onSetName = {},
        onSetBirthDate = {},
        onSetGender = {},
        onSaveCat = {},
        isFormValid = true,
        isTablet = true,
        isEditing = false,
        isFirstLaunch = true
    )
}