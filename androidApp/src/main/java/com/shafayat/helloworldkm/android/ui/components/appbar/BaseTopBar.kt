package com.shafayat.helloworldkm.android.ui.components.appbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.ui.components.text.UiText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    modifier: Modifier = Modifier,
    title: UiText = UiText.DynamicText(R.string.app_name),
    showLeftActionButton: Boolean = false,
    leftActionButtonIcon: ImageVector = Icons.Filled.ArrowBack,
    leftActionButtonDescription: String = "",
    onClickLeftActionButton: () -> Unit = {},
    showRightActionButton: Boolean = false,
    rightActionButtonIcon: ImageVector = Icons.Filled.MoreVert,
    rightActionButtonDescription: String = "",
    onClickRightActionButton: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        title = { Text(text = title.extract(LocalContext.current)) },
        navigationIcon = {
            if (showLeftActionButton) {
                IconButton(onClick = onClickLeftActionButton) {
                    Icon(leftActionButtonIcon, leftActionButtonDescription)
                }
            } else {
                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        actions = {
            if (showRightActionButton) {
                IconButton(onClick = onClickRightActionButton) {
                    Icon(rightActionButtonIcon, rightActionButtonDescription)
                }
            } else {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    )
}

@Preview
@Composable
fun BaseTopBarPreview() {
    BaseTopBar()
}

@Preview
@Composable
fun BaseTopBarWithLeftButtonPreview() {
    BaseTopBar(
        showLeftActionButton = true
    )
}

@Preview
@Composable
fun BaseTopBarWithRightButtonPreview() {
    BaseTopBar(
        showRightActionButton = true
    )
}

@Preview
@Composable
fun BaseTopBarWithLeftRightButtonPreview() {
    BaseTopBar(
        showLeftActionButton = true,
        showRightActionButton = true
    )
}