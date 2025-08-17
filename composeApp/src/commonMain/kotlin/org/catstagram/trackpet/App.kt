package org.catstagram.trackpet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.compose.setSingletonImageLoaderFactory
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.managers.settings.SettingsManager
import org.catstagram.trackpet.presentation.navigation.Screen
import org.catstagram.trackpet.presentation.screens.add_edit.AddEditScreen
import org.catstagram.trackpet.presentation.screens.chatbot.ChatBotInfoScreen
import org.catstagram.trackpet.presentation.screens.chatbot.ChatBotScreen
import org.catstagram.trackpet.presentation.screens.gallery.GalleryScreen
import org.catstagram.trackpet.presentation.screens.handbook.HandbookDetailsScreen
import org.catstagram.trackpet.presentation.screens.handbook.HandbookScreen
import org.catstagram.trackpet.presentation.screens.home.HomeScreen
import org.catstagram.trackpet.presentation.screens.news.NewsDetailScreen
import org.catstagram.trackpet.presentation.screens.news.NewsScreen
import org.catstagram.trackpet.presentation.screens.policy.PrivacyPolicyScreen
import org.catstagram.trackpet.presentation.screens.settings.SettingsScreen
import org.catstagram.trackpet.presentation.screens.settings.SettingsViewModel
import org.catstagram.trackpet.presentation.shared.BottomBar
import org.catstagram.trackpet.presentation.theme.CatstagramTheme
import org.catstagram.trackpet.util.WEB_CLIENT_ID
import org.catstagram.trackpet.util.getAsyncImageLoader
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val settings: SettingsManager = koinInject()
    val permissionsFactory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val permissionsController = remember(permissionsFactory) {
        permissionsFactory.createPermissionsController()
    }

    val settingsViewModel: SettingsViewModel = koinViewModel {
        parametersOf(permissionsController)
    }

    BindEffect(permissionsController)

    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }

    val currentScreen = navBackStackEntry?.destination?.let { currentDestination ->
        when {
            currentDestination.hasRoute(Screen.Home::class) -> Screen.Home
            currentDestination.hasRoute(Screen.ChatBot::class) -> Screen.ChatBot
            currentDestination.hasRoute(Screen.Settings::class) -> Screen.Settings
            currentDestination.hasRoute(Screen.Handbook::class) -> Screen.Handbook
            else -> Screen.Home
        }
    } ?: Screen.Home

    val startDestination = if (settings.isFirstLaunch) Screen.Add else Screen.Home

    CatstagramTheme(settingsViewModel = settingsViewModel) {
        Column {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.weight(1f)
            ) {
                composable<Screen.Add> {
                    AddEditScreen(
                        onContinue = {
                            settings.isFirstLaunch = false
                            navController.navigate(Screen.Home)
                        },
                        isFirstLaunch = settings.isFirstLaunch
                    )
                }

                composable<Screen.Edit> { backStackEntry ->
                    val editCat = backStackEntry.toRoute<Screen.Edit>()

                    AddEditScreen(
                        onContinue = {
                            navController.navigateUp()
                        },
                        isFirstLaunch = false,
                        catId = editCat.catId
                    )
                }

                composable<Screen.Home> {
                    HomeScreen(
                        toGalleryScreen = { catId ->
                            navController.navigate(Screen.Gallery(catId = catId))
                        },
                        toNewsScreen = { navController.navigate(Screen.News) },
                        onEditClick = { catId ->
                            navController.navigate(Screen.Edit(catId = catId))
                        }
                    )
                }

                composable<Screen.Gallery> { backStackEntry ->
                    val gallery = backStackEntry.toRoute<Screen.Gallery>()

                    GalleryScreen(
                        catId = gallery.catId,
                        onBackClick = { navController.navigateUp() },
                        toSettingsScreen = { navController.navigate(Screen.Settings) }
                    )
                }

                composable<Screen.News> {
                    NewsScreen(
                        onNewsClick = { newsId ->
                            navController.navigate(Screen.NewsDetail(newsId))
                        },
                        onBackClick = { navController.navigateUp() },
                        toSettingsScreen = { navController.navigate(Screen.Settings) },
                        settingsViewModel = settingsViewModel
                    )
                }

                composable<Screen.NewsDetail> { backStackEntry ->
                    val news = backStackEntry.toRoute<Screen.NewsDetail>()

                    NewsDetailScreen(
                        newsId = news.newsId,
                        onBackClick = { navController.navigateUp() },
                        toSettingsScreen = { navController.navigate(Screen.Settings) },
                        settingsViewModel = settingsViewModel
                    )
                }

                composable<Screen.Handbook> {
                    HandbookScreen(
                        onCatClick = { catId ->
                            navController.navigate(Screen.HandbookDetails(catId))
                        }
                    )
                }

                composable<Screen.HandbookDetails> { backStackEntry ->
                    val handbook = backStackEntry.toRoute<Screen.HandbookDetails>()

                    HandbookDetailsScreen(
                        catBreedId = handbook.catBreedId,
                        onBackClick = { navController.navigateUp() },
                    )
                }

                composable<Screen.ChatBot> {
                    ChatBotScreen(
                        onInfoClick = { navController.navigate(Screen.ChatBotInfo) },
                        onBackClick = { navController.navigateUp() }
                    )
                }

                composable<Screen.ChatBotInfo> {
                    ChatBotInfoScreen(onBackClick = { navController.navigateUp() })
                }

                composable<Screen.Settings> {
                    SettingsScreen(
                        settingsViewModel = settingsViewModel,
                        onPrivacyClick = { navController.navigate(Screen.Privacy) }
                    )
                }

                composable<Screen.Privacy> {
                    PrivacyPolicyScreen(
                        onBackClick = { navController.navigateUp() },
                        settingsViewModel = settingsViewModel
                    )
                }
            }

            val shouldShowBottomBar = navBackStackEntry?.destination?.let { currentDestination ->
                currentDestination.hasRoute(Screen.Home::class) ||
                        currentDestination.hasRoute(Screen.Handbook::class) ||
                        currentDestination.hasRoute(Screen.Settings::class)
            } ?: false

            if (shouldShowBottomBar) {
                BottomBar(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                    currentDestination = currentScreen,
                    onTabClick = { route ->
                        navController.navigate(route) {
                            when (route) {
                                is Screen.Home -> {
                                    popUpTo<Screen.Home> { inclusive = true }
                                }

                                else -> {
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    LaunchedEffect(navController) {
        Analytics.setupNavigationTracking(navController)
    }

    DisposableEffect(Unit) {
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(
                serverId = WEB_CLIENT_ID
            )
        )
        onDispose { }
    }
}