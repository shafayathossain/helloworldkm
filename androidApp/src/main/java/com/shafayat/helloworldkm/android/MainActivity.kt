package com.shafayat.helloworldkm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shafayat.helloworldkm.android.navigation.MainNavHost
import com.shafayat.helloworldkm.android.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModels()

        /**
         * It will apply the postSplashScreenTheme
         * after the splash screen has shown.
         */
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                viewModel.isLoading
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppTheme() {
                navHostController = rememberNavController()
                MainNavHost(navHostController)
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme() {
        GreetingView("Hello, Android!")
    }
}