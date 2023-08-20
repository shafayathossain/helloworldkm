package com.shafayat.helloworldkm.android.base

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.ui.components.appbar.BaseTopBar
import com.shafayat.helloworldkm.android.ui.components.appbar.TopBarWithBackArrow
import com.shafayat.helloworldkm.android.ui.components.bottomnavbar.BottomNavBar
import com.shafayat.helloworldkm.android.ui.components.dialog.ErrorDialog
import com.shafayat.helloworldkm.android.ui.components.dialog.LoadingDialog
import com.shafayat.helloworldkm.android.ui.components.text.UiText
import kotlinx.coroutines.launch

abstract class BaseScreen<V : BaseViewModel<out BaseEvent>> {

    lateinit var viewModel: V
    private lateinit var navHostController: NavHostController

    open val title: UiText = UiText.DynamicText(R.string.app_name)
    open val hideStatusBarAndNavigationBar: Boolean = false
    open val showTopBar: Boolean = true
    open val showBackArrow: Boolean = true
    open val showBottomBar: Boolean = false
    open val onClickPositiveButtonOfErrorDialog: () -> Unit = {}
    open val onClickNegativeButtonOfErrorDialog: () -> Unit = {}
    open val topBar: @Composable () -> Unit = {
        if (showBackArrow) TopBarWithBackArrow(
            title = title,
            navHostController = navHostController
        )
        else BaseTopBar(title = title)
    }

    open val bottomBar: @Composable () -> Unit = {
        BottomNavBar(
            navHostController = navHostController,
            items = listOf(),
            modifier = Modifier,
            onItemClick = { }
        )
    }

    @Composable
    fun BaseComposable(navHostController: NavHostController) {
        this.navHostController = navHostController
        viewModel = getViewModel()
        val context = LocalContext.current
        val state = viewModel.baseState
        val snackbarHostState = remember { SnackbarHostState() }
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        LaunchedEffect(key1 = context) {
            viewModel.baseUiEvent.collect { event ->
                when (event) {
                    is BaseUiEvent.Navigate -> {
                        navHostController.navigate(event.route)
                    }

                    BaseUiEvent.PopBackStack -> {
                        navHostController.popBackStack()
                    }

                    is BaseUiEvent.ShowSnackBar -> {
                        this.launch {
                            snackbarHostState.showSnackbar(
                                message = event.message.extract(context)
                            )
                        }
                    }

                    is BaseUiEvent.ShowToast -> {
                        Toast.makeText(context, event.message.extract(context), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        DisposableEffect(systemUiController, useDarkIcons) {
            // Update all of the system bar colors to be transparent, and use
            // dark icons if we're in light theme
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )

            // setStatusBarColor() and setNavigationBarColor() also exist

            onDispose {}
        }

        Scaffold(
            topBar = { if (showTopBar) topBar() },
            bottomBar = { if (showBottomBar) bottomBar() },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            var modifier: Modifier = Modifier
            if (!hideStatusBarAndNavigationBar) {
                modifier = modifier.padding(paddingValues)
            }
            Box(modifier = modifier) {
                LoadingDialog(
                    isVisible = state.isVisibleLoadingDialog,
                    onDisMissRequest = { viewModel.onBaseEvent(BaseEvent.OnDisMissLoadingDialog) }
                )
                ErrorDialog(
                    isVisible = state.isVisibleErrorDialog,
                    onDisMissRequest = { viewModel.onBaseEvent(BaseEvent.OnDisMissErrorDialog) },
                    onClickPositiveButton = onClickPositiveButtonOfErrorDialog,
                    onClickNegativeButton = onClickNegativeButtonOfErrorDialog
                )
                ScreenContent()
            }
        }
    }

    @Composable
    protected abstract fun ScreenContent()

    @Composable
    protected abstract fun getViewModel(): V
}