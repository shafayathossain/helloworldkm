package com.shafayat.helloworldkm.android.ui.components.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.ui.components.text.UiText

@Composable
fun TopBarWithBackArrow(
    modifier: Modifier = Modifier,
    title: UiText = UiText.DynamicText(R.string.app_name),
    navHostController: NavHostController,
) {
    BaseTopBar(
        modifier = modifier,
        title = title,
        showLeftActionButton = (navHostController.previousBackStackEntry != null),
        leftActionButtonIcon = Icons.Filled.ArrowBack,
        onClickLeftActionButton = {
            navHostController.navigateUp()
        }
    )
}

@Preview
@Composable
fun TopBarWithBackArrowPreview() {
    TopBarWithBackArrow(navHostController = rememberNavController())
}