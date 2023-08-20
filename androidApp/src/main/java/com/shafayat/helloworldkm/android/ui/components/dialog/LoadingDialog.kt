package com.shafayat.helloworldkm.android.ui.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.ui.components.text.UiText
import com.shafayat.helloworldkm.android.ui.theme.LocalSpacing

@Composable
fun LoadingDialog(
    loadingText: UiText = UiText.DynamicText(R.string.loading),
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
            modifier = Modifier.padding(LocalSpacing.current.large)
        ) {
            Text(
                text = loadingText.extract(LocalContext.current),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.small))
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun PreviewLoading() {
    var isLoading by remember { mutableStateOf(true) }
    LoadingDialog(
        isVisible = isLoading,
        onDisMissRequest = { isLoading = false }
    )
}