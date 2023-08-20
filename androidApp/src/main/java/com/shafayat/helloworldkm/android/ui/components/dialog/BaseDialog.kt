package com.shafayat.helloworldkm.android.ui.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CrisisAlert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.ui.components.text.UiText
import com.shafayat.helloworldkm.android.ui.theme.LocalSpacing

@Composable
fun BaseDialog(
    icon: ImageVector = Icons.Filled.CrisisAlert,
    iconDescription: String = stringResource(R.string.description),
    title: UiText = UiText.DynamicText(R.string.title),
    description: UiText = UiText.DynamicText(R.string.lorem_ipsum),
    positiveButtonText: UiText = UiText.DynamicText(R.string.yes),
    negativeButtonText: UiText = UiText.DynamicText(R.string.no),
    isVisiblePositiveActionButton: Boolean = false,
    isVisibleNegativeActionButton: Boolean = false,
    onClickPositiveButton: () -> Unit = {},
    onClickNegativeButton: () -> Unit = {},
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    isVisible: Boolean = true,
    onDisMissRequest: () -> Unit,
) {
    if (!isVisible) return

    Dialog(
        onDismissRequest = onDisMissRequest,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(LocalSpacing.current.small)
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = iconDescription,
                modifier = Modifier.size(LocalSpacing.current.extraLarge)
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.extraSmall))
            Text(
                text = title.extract(LocalContext.current),
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.extraSmall))
            Text(
                text = description.extract(LocalContext.current),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                maxLines = 5
            )
            if (isVisiblePositiveActionButton || isVisibleNegativeActionButton) {
                Spacer(modifier = Modifier.size(LocalSpacing.current.extraSmall))
                Row(
                    modifier = Modifier.padding(LocalSpacing.current.small)
                ) {
                    if (isVisiblePositiveActionButton) {
                        Button(onClick = onClickPositiveButton) {
                            Text(positiveButtonText.extract(LocalContext.current))
                        }
                    }
                    if (isVisibleNegativeActionButton) {
                        Spacer(modifier = Modifier.size(LocalSpacing.current.small))
                        OutlinedButton(onClick = onClickNegativeButton) {
                            Text(negativeButtonText.extract(LocalContext.current))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogPreviewNoActionButton() {
    var isVisible by remember { mutableStateOf(true) }
    BaseDialog(
        isVisible = isVisible,
        onDisMissRequest = { isVisible = false },
    )
}

@Preview
@Composable
fun BaseDialogPreviewBothActionButton() {
    var isVisible by remember { mutableStateOf(true) }
    BaseDialog(
        isVisible = isVisible,
        onDisMissRequest = { isVisible = false },
        onClickNegativeButton = { isVisible = false },
        isVisiblePositiveActionButton = true,
        isVisibleNegativeActionButton = true
    )
}


@Preview
@Composable
fun BaseDialogPreviewOneActionButton() {
    var isVisible by remember { mutableStateOf(true) }
    BaseDialog(
        isVisible = isVisible,
        onDisMissRequest = { isVisible = false },
        isVisiblePositiveActionButton = true,
        positiveButtonText = UiText.DynamicText(R.string.ok),
        onClickPositiveButton = { isVisible = false },
        isVisibleNegativeActionButton = false
    )
}