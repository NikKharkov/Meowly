package org.catstagram.trackpet

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.mmk.kmpnotifier.notification.NotifierManager
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import org.catstagram.trackpet.di.initKoin
import org.koin.android.ext.koin.androidContext

class CatstagramApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this@CatstagramApplication) { status ->
            println("AdMob initialized: ${status.adapterStatusMap}")
        }
        NotifierManager.initialize(
            configuration = NotificationPlatformConfiguration.Android(
                notificationIconResId = R.drawable.ic_notification,
                showPushNotification = true
            )
        )
        initKoin {
            androidContext(this@CatstagramApplication)
        }
    }
}