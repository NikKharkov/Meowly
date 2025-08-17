package org.catstagram.trackpet.presentation.screens.handbook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.coat
import catstagrammp.composeapp.generated.resources.ic_coat
import catstagrammp.composeapp.generated.resources.ic_earth
import catstagrammp.composeapp.generated.resources.ic_ruler
import catstagrammp.composeapp.generated.resources.origin
import catstagrammp.composeapp.generated.resources.size
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CharacteristicsComponent(
    modifier: Modifier = Modifier,
    origin: String,
    size: String,
    coat: String
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(dimensions.paddingMedium)
            )
            .padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CatCharacteristic(
            icon = painterResource(Res.drawable.ic_earth),
            label = stringResource(Res.string.origin),
            value = origin
        )

        CatCharacteristic(
            icon = painterResource(Res.drawable.ic_ruler),
            label = stringResource(Res.string.size),
            value = size
        )

        CatCharacteristic(
            icon = painterResource(Res.drawable.ic_coat),
            label = stringResource(Res.string.coat),
            value = coat
        )
    }
}

@Composable
private fun CatCharacteristic(
    icon: Painter,
    label: String,
    value: String
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onBackground,
            textAlign = TextAlign.Center
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.onBackground,
            textAlign = TextAlign.Center
        )
    }
}