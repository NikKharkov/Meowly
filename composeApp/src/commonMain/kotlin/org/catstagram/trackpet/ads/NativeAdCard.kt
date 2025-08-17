package org.catstagram.trackpet.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_image
import coil3.compose.AsyncImage
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.ShadowGradient
import org.catstagram.trackpet.util.AD_CLICKED
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource

@Composable
fun NativeAdCard(
    imageUrl: String?,
    title: String?,
    description: String? = null,
    callToAction: String? = null,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .pressAnimation(),
        onClick = {
            onClick()
            Analytics.trackFeatureUsed(AD_CLICKED)
        },
        shape = RoundedCornerShape(dimensions.paddingMedium),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = colors.catCardGradient)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = dimensions.paddingMedium,
                            topEnd = dimensions.paddingMedium
                        )
                    )
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(Res.drawable.ic_image)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = ShadowGradient)
                )

                Text(
                    text = "Ad",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(dimensions.paddingSmall)
                        .background(
                            Color.Black.copy(alpha = 0.7f),
                            RoundedCornerShape(dimensions.paddingTiny)
                        )
                        .padding(
                            horizontal = dimensions.paddingSmall,
                            vertical = dimensions.paddingTiny
                        )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(dimensions.paddingMedium),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp
                    ),
                    color = colors.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(dimensions.paddingSmall))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    description?.let { desc ->
                        Text(
                            text = desc,
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.secondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f)
                        )
                    } ?: Spacer(modifier = Modifier.weight(1f))

                    callToAction?.let { cta ->
                        Text(
                            text = cta,
                            style = MaterialTheme.typography.labelSmall,
                            color = colors.onBackground,
                            modifier = Modifier
                                .background(
                                    colors.primary,
                                    RoundedCornerShape(dimensions.paddingSmall)
                                )
                                .padding(
                                    horizontal = dimensions.paddingMedium,
                                    vertical = dimensions.paddingSmall
                                )
                        )
                    }
                }
            }
        }
    }
}
