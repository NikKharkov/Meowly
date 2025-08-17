package org.catstagram.trackpet

import androidx.compose.ui.window.ComposeUIViewController
import org.catstagram.trackpet.notifications.initNotifications
import org.catstagram.trackpet.di.initKoin
import platform.UIKit.UIViewController

lateinit var IOSBanner: () -> UIViewController

fun generateIOSBanner(): UIViewController {
    return IOSBanner()
}

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
        initNotifications()
    }
) {
    App()
}