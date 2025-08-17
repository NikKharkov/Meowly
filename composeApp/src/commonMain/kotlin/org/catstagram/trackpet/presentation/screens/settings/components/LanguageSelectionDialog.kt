package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.cancel
import catstagrammp.composeapp.generated.resources.select_language
import org.catstagram.trackpet.domain.settings.Language
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.stringResource

@Composable
fun LanguageSelectionDialog(
    currentLanguage: Language,
    onLanguageSelected: (Language) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(Res.string.select_language),
                color = colors.onBackground
            )
        },
        text = {
            Column {
                Language.entries.forEach { language ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .pressAnimation {
                                onLanguageSelected(language)
                                onDismiss()
                            }
                            .padding(vertical = dimensions.paddingSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = currentLanguage == language,
                            onClick = {
                                onLanguageSelected(language)
                                onDismiss()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colors.accent
                            )
                        )

                        Spacer(modifier = Modifier.width(dimensions.paddingSmall))

                        Text(
                            text = when (language) {
                                Language.ENGLISH -> "English"
                                Language.RUSSIAN -> "Русский"
                            },
                            color = colors.onBackground
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = stringResource(Res.string.cancel),
                    color = colors.accent
                )
            }
        },
        containerColor = colors.primary
    )
}
