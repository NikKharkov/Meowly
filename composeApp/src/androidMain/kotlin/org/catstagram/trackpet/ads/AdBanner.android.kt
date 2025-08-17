package org.catstagram.trackpet.ads

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import org.catstagram.trackpet.util.ANDROID_BANNER_ID

@Composable
actual fun AdBanner(modifier: Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).apply {
                this.adUnitId = ANDROID_BANNER_ID
                setAdSize(AdSize.LARGE_BANNER)
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}