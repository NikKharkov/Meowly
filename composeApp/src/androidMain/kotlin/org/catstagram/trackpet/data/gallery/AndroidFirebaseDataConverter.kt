package org.catstagram.trackpet.data.gallery

import dev.gitlive.firebase.storage.Data

actual fun ByteArray.toData(): Data = Data(this)