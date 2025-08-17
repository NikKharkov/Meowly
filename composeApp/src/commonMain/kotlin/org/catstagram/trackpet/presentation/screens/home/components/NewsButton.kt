package org.catstagram.trackpet.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_arrow_forward_ios
import catstagrammp.composeapp.generated.resources.ic_newspaper
import catstagrammp.composeapp.generated.resources.today_news
import catstagrammp.composeapp.generated.resources.view_all_news
import org.catstagram.trackpet.domain.news.News
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.SoftPink
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsButton(
    newsList: List<News>,
    onViewAllNewsClick: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .pressAnimation(
                scaleDown = 0.85f,
                alphaDown = 0.7f
            ),
        onClick = {},
        shape = RoundedCornerShape(dimensions.paddingMedium),
        shadowElevation = 6.dp,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = colors.catCardGradient)
                .padding(dimensions.paddingLarge),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_newspaper),
                    contentDescription = null,
                    tint = SoftPink,
                    modifier = Modifier.size(24.dp)
                )

                Text(
                    text = stringResource(Res.string.today_news),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground
                )
            }

            newsList.forEach { newsTitle ->
                Text(
                    text = "• ${newsTitle.title}",
                    fontSize = 16.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    color = colors.onBackground
                )
            }

            Spacer(modifier = Modifier.width(dimensions.paddingLarge))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colors.background, shape = RoundedCornerShape(dimensions.paddingMedium))
                    .border(
                        width = 1.dp,
                        color = colors.onBackground.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(dimensions.paddingMedium)
                    )
                    .pressAnimation(onClick = onViewAllNewsClick)
                    .padding(dimensions.paddingMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.view_all_news),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.onBackground
                )

                Spacer(modifier = Modifier.width(dimensions.paddingSmall))

                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_forward_ios),
                    contentDescription = null,
                    tint = colors.onBackground,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}