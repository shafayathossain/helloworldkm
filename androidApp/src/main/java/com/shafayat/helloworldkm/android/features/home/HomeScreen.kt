package com.shafayat.helloworldkm.android.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.base.BaseScreen
import com.shafayat.helloworldkm.android.ui.components.text.UiText
import com.shafayat.helloworldkm.android.ui.theme.AppTheme


object HomeScreen : BaseScreen<HomeViewModel>() {

    override val title = UiText.DynamicText(R.string.home)
    override val showBottomBar = false

    @Composable
    override fun ScreenContent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.home))
        }
    }

    @Composable
    override fun getViewModel(): HomeViewModel {
        return hiltViewModel()
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AppTheme() {
        HomeScreen.BaseComposable(rememberNavController())
    }
}