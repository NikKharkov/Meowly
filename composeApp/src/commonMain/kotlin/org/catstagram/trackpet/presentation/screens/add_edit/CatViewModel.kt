package org.catstagram.trackpet.presentation.screens.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import network.chaintech.kmp_date_time_picker.utils.now
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.data.local.Database
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.domain.cat.CatUiState
import org.catstagram.trackpet.domain.cat.local.CatsGender
import org.catstagram.trackpet.util.CAT_AVATAR_SET
import org.catstagram.trackpet.util.CAT_EDITED
import org.catstagram.trackpet.util.CAT_SAVED

class CatViewModel(private val database: Database) : ViewModel() {

    private val _catState = MutableStateFlow(CatUiState())
    val catState: StateFlow<CatUiState> = _catState.asStateFlow()

    init {
        viewModelScope.launch {
            database.getAllCats().collect { cats ->
                _catState.update { it.copy(cats = cats) }
            }
        }
    }

    fun setName(name: String) {
        _catState.update { it.copy(name = name) }
    }

    fun setBirthDate(date: LocalDate) {
        _catState.update { it.copy(birthDate = date) }
    }

    fun setGender(gender: CatsGender?) {
        _catState.update { it.copy(gender = gender) }
    }

    fun clearErrors() {
        _catState.update {
            it.copy(isNameError = false, isGenderError = false, error = null)
        }
    }

    fun resetState() {
        _catState.update {
            it.copy(
                name = "",
                birthDate = LocalDate.Companion.now(),
                gender = null,
                isNameError = false,
                isGenderError = false,
                error = null,
                isEditing = false,
                editingCatId = null,
                isLoading = false
            )
        }
    }

    fun saveProfilePicture(catId: Long, avatarImage: ByteArray?) {
        avatarImage ?: return

        viewModelScope.launch(Dispatchers.IO) {
            _catState.update { it.copy(isLoading = true) }
            try {
                database.setCatAvatar(catId, avatarImage)
                Analytics.trackFeatureUsed(CAT_AVATAR_SET)
                _catState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _catState.update {
                    it.copy(
                        error = e.message ?: "Oops! Something went wrong\uD83D\uDE41",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun saveCat(onSuccess: () -> Unit) {
        val currentState = _catState.value

        val nameError = currentState.name.isBlank()
        val genderError = currentState.gender == null

        _catState.update {
            it.copy(isNameError = nameError, isGenderError = genderError)
        }

        if (!nameError && !genderError) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    if (currentState.isEditing && currentState.editingCatId != null) {
                        val updatedCat = UserCat(
                            id = currentState.editingCatId,
                            name = currentState.name,
                            birthDate = currentState.birthDate.toEpochDays().toLong(),
                            gender = if (currentState.gender == CatsGender.MALE) "M" else "F",
                            avatarImg = null
                        )
                        database.updateCat(
                            id = updatedCat.id,
                            name = updatedCat.name,
                            gender = updatedCat.gender,
                            birthDate = updatedCat.birthDate
                        )
                        Analytics.trackFeatureUsed(CAT_EDITED)
                    } else {
                        database.insertNewCat(
                            name = currentState.name,
                            gender = if (currentState.gender == CatsGender.MALE) "M" else "F",
                            birthDate = currentState.birthDate.toEpochDays().toLong()
                        )
                        Analytics.trackFeatureUsed(CAT_SAVED)
                    }

                    resetState()

                    withContext(Dispatchers.Main) {
                        onSuccess()
                    }
                } catch (e: Exception) {
                    _catState.update {
                        it.copy(error = e.message ?: "Oops! Something went wrong\uD83D\uDE41")
                    }
                }
            }
        }
    }

    fun startEditing(catId: Long) {
        viewModelScope.launch {
            _catState.first { it.cats.isNotEmpty() }

            val currentCats = _catState.value.cats
            val catToEdit = currentCats.find { it.id == catId }

            if (catToEdit != null) {
                _catState.update {
                    it.copy(
                        name = catToEdit.name,
                        birthDate = LocalDate.Companion.fromEpochDays(catToEdit.birthDate.toInt()),
                        gender = if (catToEdit.gender == "M") CatsGender.MALE else CatsGender.FEMALE,
                        isEditing = true,
                        editingCatId = catId,
                        isNameError = false,
                        isGenderError = false,
                        error = null
                    )
                }
            }
        }
    }
}