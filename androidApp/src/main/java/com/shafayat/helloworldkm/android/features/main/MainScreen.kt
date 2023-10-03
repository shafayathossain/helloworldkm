package com.shafayat.helloworldkm.android.features.main

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.base.BaseScreen
import com.shafayat.helloworldkm.android.ui.components.text.UiText
import com.shafayat.helloworldkm.android.ui.theme.AppTheme


object MainScreen : BaseScreen<MainViewModel>() {

    override val title = UiText.DynamicText(R.string.app_name)
    override val showBottomBar = true

    @Composable
    override fun ScreenContent() {
        Box(modifier = Modifier)
    }

    @Composable
    override fun getViewModel(): MainViewModel {
        return hiltViewModel()
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    AppTheme() {
        MainScreen.BaseComposable(rememberNavController())
    }
}