package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.current_language
import catstagrammp.composeapp.generated.resources.ic_arrow_forward_ios
import catstagrammp.composeapp.generated.resources.ic_en
import catstagrammp.composeapp.generated.resources.ic_ru
import catstagrammp.composeapp.generated.resources.language
import org.catstagram.trackpet.domain.settings.Language
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LanguageSettingRow(
    currentLanguage: Language,
    onLanguageClick: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(dimensions.paddingMedium)
            )
            .pressAnimation(onClick = onLanguageClick)
            .padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
        ) {
            Icon(
                painter = painterResource(resource = when (currentLanguage) {
                    Language.ENGLISH -> Res.drawable.ic_en
                    Language.RUSSIAN -> Res.drawable.ic_ru
                }),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )

            Column {
                Text(
                    text = stringResource(Res.string.language),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground
                )
                Text(
                    text = stringResource(Res.string.current_language),
                    fontSize = 14.sp,
                    color = colors.secondary
                )
            }
        }

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_forward_ios),
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier.size(16.dp)
        )
    }
}