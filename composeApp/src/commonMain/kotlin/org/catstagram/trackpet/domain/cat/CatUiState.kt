package org.catstagram.trackpet.domain.cat

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.utils.now
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.domain.cat.local.CatsGender

@Immutable
data class CatUiState(
    val name: String = "",
    val isNameError: Boolean = false,
    val birthDate: LocalDate = LocalDate.now(),
    val gender: CatsGender? = null,
    val isGenderError: Boolean = false,
    val cats: List<UserCat> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isEditing: Boolean = false,
    val editingCatId: Long? = null
)
