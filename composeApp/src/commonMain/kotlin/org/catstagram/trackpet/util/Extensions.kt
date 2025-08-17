package org.catstagram.trackpet.util

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

fun Long.toFormattedDate(): String {
    val birthDate = LocalDate.fromEpochDays(this.toInt())

    return birthDate.format(LocalDate.Format {
        dayOfMonth()
        char('.')
        monthNumber()
        char('.')
        year()
    })
}

fun String.formatAsNewsDate(language: String = "en"): String {
    return try {
        val newsDateTime = LocalDateTime.parse(this)
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val newsDate = newsDateTime.date
        val today = now.date

        when (newsDate) {
            today -> {
                val minutesDiff = (now.hour * 60 + now.minute) - (newsDateTime.hour * 60 + newsDateTime.minute)
                when {
                    minutesDiff < 1 -> if (language == "ru") "только что" else "just now"
                    minutesDiff < 60 -> if (language == "ru") "$minutesDiff мин назад" else "$minutesDiff min ago"
                    minutesDiff < 1440 -> {
                        val hoursDiff = minutesDiff / 60
                        if (language == "ru") "$hoursDiff ч назад" else "$hoursDiff h ago"
                    }
                    else -> if (language == "ru") "сегодня" else "today"
                }
            }
            today.minus(1, DateTimeUnit.DAY) -> {
                if (language == "ru") "вчера" else "yesterday"
            }
            else -> {
                val day = newsDate.dayOfMonth.toString().padStart(2, '0')
                val month = newsDate.monthNumber.toString().padStart(2, '0')
                val year = newsDate.year
                "$day.$month.$year"
            }
        }
    } catch (e: Exception) {
        try {
            val datePart = this.take(10)
            val parts = datePart.split("-")
            if (parts.size == 3) {
                "${parts[2]}.${parts[1]}.${parts[0]}"
            } else {
                this.take(10)
            }
        } catch (e: Exception) {
            this.take(10)
        }
    }
}

fun Modifier.pressAnimation(
    enabled: Boolean = true,
    scaleDown: Float = 0.95f,
    alphaDown: Float = 0.8f,
    onClick: () -> Unit = {}
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) scaleDown else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "press_scale"
    )

    val alpha by animateFloatAsState(
        targetValue = if (isPressed && enabled) alphaDown else 1f,
        animationSpec = tween(150),
        label = "press_alpha"
    )

    this.then(
        Modifier
            .scale(scale)
            .alpha(alpha)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled
            ) {
                onClick()
            }
    )
}
