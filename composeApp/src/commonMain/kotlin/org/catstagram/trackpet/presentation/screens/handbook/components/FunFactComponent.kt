package org.catstagram.trackpet.presentation.screens.handbook.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.fun_fact
import catstagrammp.composeapp.generated.resources.ic_star
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.PrimaryGradient
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FunFactComponent(
    modifier: Modifier = Modifier,
    funFact: String
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = PrimaryGradient,
                shape = RoundedCornerShape(dimensions.paddingLarge)
            )
            .padding(dimensions.paddingMedium),
        horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_star),
            contentDescription = stringResource(Res.string.fun_fact),
            modifier = Modifier.size(40.dp)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
        ) {
            Text(
                text = stringResource(Res.string.fun_fact),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )

            Text(
                text = funFact,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.onBackground
            )
        }
    }
}