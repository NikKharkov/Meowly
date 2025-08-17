package org.catstagram.trackpet.analytics

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.analytics.Event
import dev.gitlive.firebase.analytics.Param
import dev.gitlive.firebase.analytics.analytics
import dev.gitlive.firebase.analytics.logEvent
import org.catstagram.trackpet.presentation.navigation.Screen

object Analytics {
    val analytics = Firebase.analytics
    fun trackScreenView(screenName: String) {
        analytics.logEvent(analytics.Event.SCREEN_VIEW) {
            param(analytics.Param.SCREEN_NAME, screenName)
        }
    }

    fun trackFeatureUsed(featureName: String) {
        analytics.logEvent(analytics.Event.SELECT_ITEM) {
            param(analytics.Param.ITEM_NAME, featureName)
        }
    }

    fun trackError(error: String) {
        analytics.logEvent("logged_error") {
            param("error",error)
        }
    }

    private fun getScreenName(destination: NavDestination): String {
        return when {
            destination.hasRoute(Screen.Home::class) -> ScreenNames.HOME
            destination.hasRoute(Screen.Add::class) -> ScreenNames.ADD_CAT
            destination.hasRoute(Screen.Edit::class) -> ScreenNames.EDIT_CAT
            destination.hasRoute(Screen.Gallery::class) -> ScreenNames.GALLERY
            destination.hasRoute(Screen.News::class) -> ScreenNames.NEWS
            destination.hasRoute(Screen.NewsDetail::class) -> ScreenNames.NEWS_DETAIL
            destination.hasRoute(Screen.Handbook::class) -> ScreenNames.HANDBOOK
            destination.hasRoute(Screen.ChatBot::class) -> ScreenNames.CHATBOT
            destination.hasRoute(Screen.ChatBotInfo::class) -> ScreenNames.CHATBOT_INFO
            destination.hasRoute(Screen.Settings::class) -> ScreenNames.SETTINGS
            else -> "unknown"
        }
    }

    fun setupNavigationTracking(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val screenName = getScreenName(destination)
            trackScreenView(screenName)
        }
    }

    object ScreenNames {
        const val HOME = "home"
        const val ADD_CAT = "add_cat"
        const val EDIT_CAT = "edit_cat"
        const val GALLERY = "gallery"
        const val NEWS = "news"
        const val NEWS_DETAIL = "news_detail"
        const val HANDBOOK = "handbook"
        const val CHATBOT = "chatbot"
        const val CHATBOT_INFO = "chatbot_info"
        const val SETTINGS = "settings"
    }
}