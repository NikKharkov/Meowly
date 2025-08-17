package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.cancel
import catstagrammp.composeapp.generated.resources.notification_permission_message
import catstagrammp.composeapp.generated.resources.notification_permission_title
import catstagrammp.composeapp.generated.resources.open_settings
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotificationPermissionDialog(
    onOpenSettings: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(Res.string.notification_permission_title)
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.notification_permission_message)
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onOpenSettings()
                    onDismiss()
                }
            ) {
                Text(
                    text = stringResource(Res.string.open_settings)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(Res.string.cancel)
                )
            }
        }
    )
}