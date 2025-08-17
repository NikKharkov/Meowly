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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_smile
import catstagrammp.composeapp.generated.resources.personality
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PersonalityComponent(
    modifier: Modifier = Modifier,
    qualities: List<StringResource>
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colors.primary, shape = RoundedCornerShape(dimensions.paddingMedium))
            .padding(dimensions.paddingMedium),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_smile),
                contentDescription = stringResource(Res.string.personality),
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = stringResource(Res.string.personality),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )
        }

        qualities.forEach { item ->
            Text(
                text = "· ${stringResource(item)}",
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )
        }
    }
}