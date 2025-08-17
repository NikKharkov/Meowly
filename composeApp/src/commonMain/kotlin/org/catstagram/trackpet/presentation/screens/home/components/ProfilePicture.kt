package org.catstagram.trackpet.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.default_cat_avatar
import coil3.compose.AsyncImage
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.presentation.theme.AvatarBackgroundGradient
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfilePicture(
    saveImage: (Long, ByteArray?) -> Unit,
    cat: UserCat
) {
    Box(
        modifier = Modifier.size(100.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        if (cat.avatarImg != null) {
            AsyncImage(
                model = cat.avatarImg,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(brush = AvatarBackgroundGradient),
                contentScale = ContentScale.Crop,
            )
        } else {
            Image(
                painter = painterResource(Res.drawable.default_cat_avatar),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(brush = AvatarBackgroundGradient),
                contentScale = ContentScale.Crop,
            )
        }

        AddAvatarButton(
            saveImage = saveImage,
            cat = cat
        )
    }
}