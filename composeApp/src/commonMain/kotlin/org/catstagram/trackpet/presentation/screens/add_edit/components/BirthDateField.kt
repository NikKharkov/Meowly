package org.catstagram.trackpet.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.birthdate
import catstagrammp.composeapp.generated.resources.icon_calendar
import catstagrammp.composeapp.generated.resources.done
import catstagrammp.composeapp.generated.resources.ic_calendar
import catstagrammp.composeapp.generated.resources.select_birthdate
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.WheelPickerDefaults
import network.chaintech.kmp_date_time_picker.utils.now
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BirthDateField(
    birthDate: LocalDate,
    onSetBirthDate: (LocalDate) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isShowingPicker by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        BirthDateFieldHeader()
        BirthDateSelector(
            selectedDate = birthDate,
            onClick = { isShowingPicker = true }
        )
        if (isShowingPicker) {
            StyledDatePicker(
                currentDate = birthDate,
                onDateSelected = onSetBirthDate,
                onDismiss = { isShowingPicker = false }
            )
        }
    }
}

@Composable
private fun BirthDateFieldHeader() {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = dimensions.paddingMedium, vertical = dimensions.paddingSmall)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_calendar),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = stringResource(Res.string.birthdate),
            fontSize = 16.sp,
            color = colors.onBackground,
            modifier = Modifier.padding(start = dimensions.paddingMedium)
        )
    }
}


@Composable
private fun BirthDateSelector(
    selectedDate: LocalDate,
    onClick: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.paddingMedium)
            .pressAnimation(),
        shape = RoundedCornerShape(12.dp),
        color = colors.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatBirthDate(selectedDate),
                fontSize = 16.sp,
                color = colors.secondary
            )
            Icon(
                painter = painterResource(Res.drawable.icon_calendar),
                contentDescription = null,
                tint = colors.secondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
@Composable
private fun StyledDatePicker(
    currentDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalAppColors.current
    val today = LocalDate.now()

    WheelDatePickerView(
        modifier = Modifier,
        showDatePicker = true,
        title = stringResource(Res.string.select_birthdate),
        doneLabel = stringResource(Res.string.done),
        startDate = currentDate,
        minDate = LocalDate(1900, 1, 1),
        maxDate = today,
        yearsRange = IntRange(1900, today.year),
        height = 200.dp,
        rowCount = 3,
        showShortMonths = false,
        showMonthAsNumber = false,
        hideHeader = false,
        containerColor = colors.primary,
        shape = RoundedCornerShape(16.dp),
        dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
        titleStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colors.secondary
        ),
        doneLabelStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colors.secondary
        ),
        defaultDateTextStyle = TextStyle(
            fontSize = 16.sp,
            color = colors.secondary
        ),
        selectedDateTextStyle = TextStyle(
            fontSize = 16.sp,
            color = colors.secondary
        ),
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = true,
            borderColor = colors.primary.copy(alpha = 0.3f),
        ),
        onDoneClick = { selectedDate ->
            onDateSelected(selectedDate)
            onDismiss()
        },
        onDateChangeListener = { },
        onDismiss = onDismiss
    )
}

private fun formatBirthDate(date: LocalDate): String {
    return "${date.dayOfMonth}.${date.month.number.toString().padStart(2, '0')}.${date.year}"
}