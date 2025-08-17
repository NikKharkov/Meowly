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
import catstagrammp.composeapp.generated.resources.care_tips
import catstagrammp.composeapp.generated.resources.ic_scissors
import catstagrammp.composeapp.generated.resources.personality
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CareTipsComponent(
    modifier: Modifier = Modifier,
    tip: String
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(dimensions.paddingMedium)
            )
            .padding(dimensions.paddingMedium),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_scissors),
                contentDescription = stringResource(Res.string.personality),
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = stringResource(Res.string.care_tips),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )
        }

        Text(
            text = tip,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            lineHeight = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = colors.onBackground
        )
    }
}