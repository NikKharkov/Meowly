package org.catstagram.trackpet.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.female
import catstagrammp.composeapp.generated.resources.ic_calendar
import catstagrammp.composeapp.generated.resources.ic_edit
import catstagrammp.composeapp.generated.resources.ic_female
import catstagrammp.composeapp.generated.resources.ic_male
import catstagrammp.composeapp.generated.resources.male
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.presentation.theme.AccentColor
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.SecondaryAccent
import org.catstagram.trackpet.presentation.theme.SoftBlue
import org.catstagram.trackpet.presentation.theme.SoftPink
import org.catstagram.trackpet.util.toFormattedDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CatInfo(
    modifier: Modifier = Modifier,
    cat: UserCat,
    onEditClick: (Long) -> Unit
) {
    val dimensions = LocalAppDimensions.current
    val colors = LocalAppColors.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
        ) {
            Text(
                text = cat.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                color = colors.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            IconButton(
                onClick = { onEditClick(cat.id) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_edit),
                    contentDescription = null,
                    tint = AccentColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        InfoRow(
            icon = if (cat.gender == "F")
                painterResource(Res.drawable.ic_female)
            else
                painterResource(Res.drawable.ic_male),
            text = stringResource(if (cat.gender == "F") Res.string.female else Res.string.male),
            iconTint = if (cat.gender == "F") SoftPink else SoftBlue
        )

        InfoRow(
            icon = painterResource(Res.drawable.ic_calendar),
            text = cat.birthDate.toFormattedDate(),
            iconTint = SecondaryAccent
        )
    }
}

@Composable
private fun InfoRow(
    icon: Painter,
    text: String,
    iconTint: Color
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Surface(
            modifier = Modifier.size(24.dp),
            shape = CircleShape,
            color = iconTint.copy(alpha = 0.15f)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensions.paddingTiny)
            )
        }

        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            color = colors.secondary
        )
    }
}
