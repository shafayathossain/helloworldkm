package com.shafayat.helloworldkm.android.ui.components.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.ui.components.text.UiText

@Composable
fun ErrorDialog(
    icon: ImageVector = Icons.Filled.Error,
    iconDescription: String = stringResource(R.string.error),
    title: UiText = UiText.DynamicText(R.string.error),
    description: UiText = UiText.DynamicText(R.string.something_went_wrong),
    isVisiblePositiveActionButton: Boolean = true,
    isVisibleNegativeActionButton: Boolean = true,
    positiveButtonText: UiText = UiText.DynamicText(R.string.try_again),
    negativeButtonText: UiText = UiText.DynamicText(R.string.go_back),
    onClickPositiveButton: () -> Unit = {},
    onClickNegativeButton: () -> Unit = {},
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    isVisible: Boolean = true,
    onDisMissRequest: () -> Unit,
) {
    BaseDialog(
        icon = icon,
        iconDescription = iconDescription,
        title = title,
        description = description,
        positiveButtonText = positiveButtonText,
        negativeButtonText = negativeButtonText,
        onClickPositiveButton = onClickPositiveButton,
        onClickNegativeButton = onClickNegativeButton,
        dismissOnBackPress = dismissOnBackPress,
        dismissOnClickOutside = dismissOnClickOutside,
        isVisible = isVisible,
        onDisMissRequest = onDisMissRequest,
        isVisiblePositiveActionButton = isVisiblePositiveActionButton,
        isVisibleNegativeActionButton = isVisibleNegativeActionButton
    )
}

@Preview
@Composable
fun ErrorDialogPreview() {
    var isError by remember { mutableStateOf(true) }
    ErrorDialog(
        isVisible = isError,
        onDisMissRequest = { isError = false },
        onClickNegativeButton = { isError = false }
    )
}