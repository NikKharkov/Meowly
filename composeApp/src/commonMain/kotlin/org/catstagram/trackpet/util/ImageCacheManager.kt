package org.catstagram.trackpet.util

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.key.Keyer
import coil3.memory.MemoryCache
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.Options
import coil3.request.crossfade
import coil3.util.DebugLogger
import okio.FileSystem

class FirebaseCacher : Keyer<String> {
    override fun key(data: String, options: Options): String {
        return if (data.contains("firebasestorage.googleapis.com")) {
            data.substringBefore("?alt=media")
        } else {
            data
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context)
        .components {
            add(KtorNetworkFetcherFactory())
            add(FirebaseCacher())
        }
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.25)
                .strongReferencesEnabled(true)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "gallery_cache")
                .maxSizeBytes(250L * 1024 * 1024)
                .build()
        }
        .crossfade(true)
        .logger(DebugLogger())
        .build()
