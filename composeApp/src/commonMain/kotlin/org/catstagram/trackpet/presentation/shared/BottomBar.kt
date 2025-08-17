package org.catstagram.trackpet.presentation.shared

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.add
import catstagrammp.composeapp.generated.resources.chat_bot
import catstagrammp.composeapp.generated.resources.handbook
import catstagrammp.composeapp.generated.resources.home
import catstagrammp.composeapp.generated.resources.ic_add
import catstagrammp.composeapp.generated.resources.ic_bot
import catstagrammp.composeapp.generated.resources.ic_handbook
import catstagrammp.composeapp.generated.resources.ic_home
import catstagrammp.composeapp.generated.resources.ic_settings
import catstagrammp.composeapp.generated.resources.settings
import org.catstagram.trackpet.presentation.navigation.Screen
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.PrimaryGradient
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentDestination: Any?,
    onTabClick: (Any) -> Unit
) {
    val colors = LocalAppColors.current

    val selectedTab = when (currentDestination) {
        is Screen.Home -> BottomTab.HOME
        is Screen.Handbook -> BottomTab.HANDBOOK
        is Screen.Add -> BottomTab.ADD
        is Screen.ChatBot -> BottomTab.CHATBOT
        is Screen.Settings -> BottomTab.SETTINGS
        else -> BottomTab.HOME
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(colors.primary),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomTab.entries.forEach { tab ->
            if (tab == BottomTab.ADD) {
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()

                val addButtonScale by animateFloatAsState(
                    targetValue = if (isPressed) 0.85f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    label = "add_button_scale"
                )

                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .scale(addButtonScale)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onTabClick(tab.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(70.dp)
                    )
                }
            } else {
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                val isSelected = selectedTab == tab

                val tabScale by animateFloatAsState(
                    targetValue = if (isPressed) 0.9f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessHigh
                    ),
                    label = "tab_scale"
                )

                val selectedIconScale by animateFloatAsState(
                    targetValue = if (isSelected) 1.1f else 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    label = "selected_icon_scale"
                )

                val backgroundScale by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0.8f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    label = "background_scale"
                )

                val backgroundAlpha by animateFloatAsState(
                    targetValue = if (isSelected) 1f else 0f,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    ),
                    label = "background_alpha"
                )

                Column(
                    modifier = Modifier
                        .scale(tabScale)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onTabClick(tab.route) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .scale(backgroundScale)
                                    .alpha(backgroundAlpha)
                                    .background(
                                        brush = PrimaryGradient,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            )
                        }

                        Icon(
                            painter = painterResource(tab.icon),
                            contentDescription = null,
                            tint = if (isSelected) Color.White else Color.Gray,
                            modifier = Modifier
                                .size(24.dp)
                                .scale(selectedIconScale)
                        )
                    }

                    Text(
                        text = stringResource(tab.label),
                        style = TextStyle(
                            fontSize = 12.sp,
                            brush = if (isSelected) {
                                PrimaryGradient
                            } else {
                                SolidColor(Color.Gray)
                            }
                        ),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

private enum class BottomTab(
    val label: StringResource,
    val icon: DrawableResource,
    val route: Any,
) {
    HOME(
        label = Res.string.home,
        icon = Res.drawable.ic_home,
        route = Screen.Home
    ),
    HANDBOOK(
        label = Res.string.handbook,
        icon = Res.drawable.ic_handbook,
        route = Screen.Handbook
    ),
    ADD(
        label = Res.string.add,
        icon = Res.drawable.ic_add,
        route = Screen.Add
    ),
    CHATBOT(
        label = Res.string.chat_bot,
        icon = Res.drawable.ic_bot,
        route = Screen.ChatBot
    ),
    SETTINGS(
        label = Res.string.settings,
        icon = Res.drawable.ic_settings,
        route = Screen.Settings
    )
}