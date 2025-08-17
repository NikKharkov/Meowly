package org.catstagram.trackpet.managers.settings

import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import org.catstagram.trackpet.domain.chatbot.ChatMessageItem
import org.catstagram.trackpet.domain.settings.Language

class SettingsManager() {
    private val settings = Settings()

    var isFirstLaunch: Boolean
        get() = settings.getBoolean(IS_FIRST_LAUNCH, true)
        set(value) = settings.putBoolean(IS_FIRST_LAUNCH, value)

    var isDarkTheme: Boolean
        get() = settings.getBoolean(IS_DARK_THEME, false)
        set(value) = settings.putBoolean(IS_DARK_THEME, value)

    var language: Language
        get() {
            val languageCode = settings.getStringOrNull(LANGUAGE_CODE)
            return Language.entries.find { it.code == languageCode } ?: Language.ENGLISH
        }
        set(value) {
            settings.putString(LANGUAGE_CODE, value.code)
        }

    var areNotificationsEnabled: Boolean
        get() = settings.getBoolean(NOTIFICATIONS_ENABLED, false)
        set(value) = settings.putBoolean(NOTIFICATIONS_ENABLED, value)

    var chatHistory: List<ChatMessageItem>
        get() {
            val json = settings.getStringOrNull(CHAT_HISTORY)
            return if (json != null) {
                try {
                    Json.Default.decodeFromString<List<ChatMessageItem>>(json)
                } catch (e: Exception) {
                    println("Unable to load chat history: $e")
                    emptyList()
                }
            } else {
                emptyList()
            }
        }
        set(value) {
            val json = Json.Default.encodeToString(value)
            settings.putString(CHAT_HISTORY, json)
        }

    var savedCatBreeds: Set<Int>
        get() {
            val json = settings.getStringOrNull(SAVED_CAT_BREEDS)
            return if (json != null) {
                try {
                    Json.Default.decodeFromString<Set<Int>>(json)
                } catch (e: Exception) {
                    println("Unable to load saved cats: $e")
                    emptySet()
                }
            } else {
                emptySet()
            }
        }
        set(value) {
            val json = Json.Default.encodeToString(value)
            settings.putString(SAVED_CAT_BREEDS, json)
        }

    fun clearChatHistory() {
        settings.remove(CHAT_HISTORY)
    }

    companion object Companion {
        const val IS_FIRST_LAUNCH = "is_first_launch"
        const val CHAT_HISTORY = "chat_history"
        const val IS_DARK_THEME = "is_dark_theme"
        const val LANGUAGE_CODE = "language_code"
        const val NOTIFICATIONS_ENABLED = "notifications_enabled"
        const val SAVED_CAT_BREEDS = "saved_cat_breeds"
    }
}