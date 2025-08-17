package org.catstagram.trackpet.presentation.screens.home.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_camera
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddAvatarButton(
    modifier: Modifier = Modifier,
    saveImage: (Long, ByteArray?) -> Unit,
    cat: UserCat
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val scope = rememberCoroutineScope()

    val avatarPicker = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Single,
        scope = scope,
        onResult = { byteArrays ->
            saveImage(cat.id, byteArrays.firstOrNull())
        }
    )

    Surface(
        onClick = { avatarPicker.launch() },
        modifier = modifier
            .size(32.dp)
            .pressAnimation(),
        shape = CircleShape,
        color = colors.background,
        shadowElevation = 4.dp,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_camera),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.paddingSmall),
            tint = Color.Unspecified
        )
    }
}