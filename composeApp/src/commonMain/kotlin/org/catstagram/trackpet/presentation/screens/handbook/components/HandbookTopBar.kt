package org.catstagram.trackpet.presentation.screens.handbook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_bookmark
import catstagrammp.composeapp.generated.resources.ic_bookmark_filled
import catstagrammp.composeapp.generated.resources.ic_search
import catstagrammp.composeapp.generated.resources.search_cat_breeds
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HandbookTopBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    showOnlySaved: Boolean,
    onToggleSavedFilter: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensions.paddingSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = {
                Text(
                    text = stringResource(Res.string.search_cat_breeds),
                    color = colors.secondary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null,
                    tint = colors.onBackground,
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(dimensions.paddingXLarge),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colors.onBackground,
                unfocusedTextColor = colors.onBackground,
                unfocusedContainerColor = colors.primary,
                focusedContainerColor = colors.primary,
                focusedBorderColor = colors.onBackground,
                unfocusedBorderColor = colors.onBackground
            ),
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(if (showOnlySaved)
                Res.drawable.ic_bookmark_filled else Res.drawable.ic_bookmark),
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier
                .size(40.dp)
                .pressAnimation(onClick = onToggleSavedFilter)
        )
    }
}