package org.catstagram.trackpet.ads

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import org.catstagram.trackpet.util.ANDROID_NATIVE_BANNER_ID

@Composable
actual fun NativeAd(modifier: Modifier) {
    val context = LocalContext.current
    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }

    val contentViewId by remember { mutableIntStateOf(View.generateViewId()) }
    val adViewId by remember { mutableIntStateOf(View.generateViewId()) }

    when {
        nativeAd == null -> {
            NativeAdCard(
                imageUrl = null,
                title = "Loading ad...",
                description = "Please wait",
                modifier = modifier
            )
        }

        nativeAd != null -> {
            AndroidView(
                modifier = modifier,
                factory = { context ->
                    val contentView = ComposeView(context).apply {
                        id = contentViewId
                    }

                    NativeAdView(context).apply {
                        id = adViewId
                        addView(contentView)
                    }
                },
                update = { view ->
                    val adView = view.findViewById<NativeAdView>(adViewId)
                    val contentView = view.findViewById<ComposeView>(contentViewId)

                    nativeAd?.let { ad ->
                        adView.setNativeAd(ad)
                        adView.callToActionView = contentView

                        contentView.setContent {
                            NativeAdCard(
                                imageUrl = ad.images.firstOrNull()?.uri?.toString(),
                                title = ad.headline ?: "Ad",
                                description = ad.body,
                                callToAction = ad.callToAction,
                                onClick = {
                                    contentView.performClick()
                                }
                            )
                        }
                    }
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        val adLoader = AdLoader.Builder(context, ANDROID_NATIVE_BANNER_ID)
            .forNativeAd { ad -> nativeAd = ad }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    println("Native ad failed to load: ${loadAdError.message}")
                }
            })
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }
}