package org.catstagram.trackpet.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.female
import catstagrammp.composeapp.generated.resources.gender
import catstagrammp.composeapp.generated.resources.ic_female
import catstagrammp.composeapp.generated.resources.ic_gender
import catstagrammp.composeapp.generated.resources.ic_male
import catstagrammp.composeapp.generated.resources.male
import org.catstagram.trackpet.domain.cat.local.CatsGender
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GenderSelectionField(
    selectedGender: CatsGender? = null,
    onGenderChange: (CatsGender?) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val dimensions = LocalAppDimensions.current

    Column(modifier = modifier) {
        GenderFieldHeader()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.paddingMedium),
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
        ) {
            GenderCard(
                icon = painterResource(Res.drawable.ic_female),
                text = stringResource(Res.string.female),
                isSelected = selectedGender == CatsGender.FEMALE,
                onClick = { onGenderChange(CatsGender.FEMALE) },
                modifier = Modifier.weight(1f)
            )
            GenderCard(
                icon = painterResource(Res.drawable.ic_male),
                text = stringResource(Res.string.male),
                isSelected = selectedGender == CatsGender.MALE,
                onClick = { onGenderChange(CatsGender.MALE) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun GenderFieldHeader() {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = dimensions.paddingMedium, vertical = dimensions.paddingSmall)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_gender),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = stringResource(Res.string.gender),
            fontSize = 16.sp,
            color = colors.onBackground,
            modifier = Modifier.padding(start = dimensions.paddingMedium)
        )
    }
}

@Composable
private fun GenderCard(
    icon: Painter,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .pressAnimation(),
        shape = RoundedCornerShape(12.dp),
        color = colors.primary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = colors.onBackground,
                    unselectedColor = colors.secondary
                )
            )
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                color = colors.secondary,
                modifier = Modifier.padding(start = dimensions.paddingSmall)
            )
        }
    }
}