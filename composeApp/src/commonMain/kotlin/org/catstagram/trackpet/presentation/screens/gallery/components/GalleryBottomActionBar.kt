package org.catstagram.trackpet.presentation.screens.gallery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.delete
import catstagrammp.composeapp.generated.resources.ic_delete
import catstagrammp.composeapp.generated.resources.ic_share
import catstagrammp.composeapp.generated.resources.share
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.SecondaryAccent
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GalleryBottomActionBar(
    onDeletePhoto: () -> Unit,
    onSharePhoto: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .background(color = colors.primary)
            .padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingMedium),
            modifier = Modifier.pressAnimation(onClick = { onDeletePhoto() })
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_delete),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(32.dp)
            )

            Text(
                text = stringResource(Res.string.delete),
                fontSize = 16.sp,
                color = Color.Red,
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingMedium),
            modifier = Modifier.pressAnimation(onClick = { onSharePhoto() })
        ) {
            Text(
                text = stringResource(Res.string.share),
                fontSize = 16.sp,
                color = SecondaryAccent,
            )

            Icon(
                painter = painterResource(Res.drawable.ic_share),
                contentDescription = null,
                tint = SecondaryAccent,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

