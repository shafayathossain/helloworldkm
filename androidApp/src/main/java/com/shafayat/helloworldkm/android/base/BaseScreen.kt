package com.shafayat.helloworldkm.android.base

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.shafayat.helloworldkm.android.R
import com.shafayat.helloworldkm.android.navigation.HomeNavHost
import com.shafayat.helloworldkm.android.navigation.Route
import com.shafayat.helloworldkm.android.ui.components.appbar.BaseTopBar
import com.shafayat.helloworldkm.android.ui.components.appbar.TopBarWithBackArrow
import com.shafayat.helloworldkm.android.ui.components.bottomnavbar.BottomNavBar
import com.shafayat.helloworldkm.android.ui.components.dialog.ErrorDialog
import com.shafayat.helloworldkm.android.ui.components.dialog.LoadingDialog
import com.shafayat.helloworldkm.android.ui.components.text.UiText
import kotlinx.coroutines.launch

abstract class BaseScreen<V : BaseViewModel<out BaseEvent>> {

    lateinit var viewModel: V
        private set
    private lateinit var mainNavController: NavHostController

    /**
     * UI properties that can be overridden to customize the appearance and behavior of the screen.
     */
    open val title = UiText.DynamicText(R.string.app_name)
    open val hideStatusBarAndNavigationBar = false
    open val showTopBar = true
    open val showBackArrow = false
    open val showBottomBar = false
    open val onClickPositiveButtonOfErrorDialog: () -> Unit = {}
    open val onClickNegativeButtonOfErrorDialog: () -> Unit = {}

    /**
     * The top bar UI component, customizable by overriding the [showBackArrow] and [title] properties.
     */
    open val topBar: @Composable () -> Unit = {
        if (showBackArrow) {
            TopBarWithBackArrow(title = title, navHostController = mainNavController)
        } else {
            BaseTopBar(title = title)
        }
    }

    /**
     * The bottom bar UI component, customizable by overriding the [getBottomNavItems] function.
     */
    open val bottomBar: @Composable (NavHostController) -> Unit = { bottomNavController ->
        BottomNavBar(
            navHostController = bottomNavController,
            items = getBottomNavItems(),
            modifier = Modifier,
            onItemClick = { item ->
                if(item.route == Route.homeScreen) {
                    bottomNavController.popBackStack(Route.homeScreen, inclusive = false)
                } else if(bottomNavController.currentBackStack.value.count() > 2) {
                    bottomNavController.navigate(item.route) {
                        bottomNavController.popBackStack()
                    }
                } else {
                    bottomNavController.navigate(item.route) {
                        launchSingleTop = true
                    }
                }
            }
        )
    }

    /**
     * The base composable function that sets up the UI and behavior of the screen.
     */
    @Composable
    fun BaseComposable(
        mainNavController: NavHostController,
        bottomNavController: NavHostController? = null
    ) {
        this.mainNavController = mainNavController
        viewModel = getViewModel()

        val context = LocalContext.current
        val state = viewModel.baseState
        val snackbarHostState = remember { SnackbarHostState() }
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        HandleUIEvents(context, snackbarHostState)
        ConfigureSystemUI(systemUiController, useDarkIcons, MaterialTheme.colorScheme.secondary)

        Scaffold(
            topBar = { if (showTopBar) topBar() },
            bottomBar = {
                if (showBottomBar && bottomNavController != null) bottomBar(
                    bottomNavController
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { paddingValues ->
            ContentBox(paddingValues, state, context, bottomNavController)
        }
    }

    /**
     * Handles UI events like navigation, showing snack bars, and toasts.
     */
    @Composable
    private fun HandleUIEvents(context: Context, snackbarHostState: SnackbarHostState) {
        LaunchedEffect(context) {
            viewModel.baseUiEvent.collect { event ->
                when (event) {
                    is BaseUiEvent.Navigate -> mainNavController.navigate(event.route)
                    is BaseUiEvent.PopBackStack -> mainNavController.popBackStack()
                    is BaseUiEvent.ShowSnackBar -> launch {
                        snackbarHostState.showSnackbar(event.message.extract(context))
                    }
                    is BaseUiEvent.ShowToast -> Toast.makeText(
                        context, event.message.extract(context), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /**
     * Configures the system UI, including the status bar and navigation bar.
     */
    @Composable
    private fun ConfigureSystemUI(
        systemUiController: SystemUiController,
        useDarkIcons: Boolean,
        statusBarColor: Color
    ) {
        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(statusBarColor, useDarkIcons)
            onDispose { }
        }
    }

    /**
     * The content area of the screen, including loading and error dialogs, and the main screen content.
     */
    @Composable
    private fun ContentBox(
        paddingValues: PaddingValues,
        state: BaseUiState,
        context: Context,
        bottomNavController: NavHostController?
    ) {
        Box(
            modifier = if (!hideStatusBarAndNavigationBar) {
                Modifier.padding(paddingValues)
            } else Modifier
        ) {
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
            if (showBottomBar && bottomNavController != null) {
                HomeNavHost(mainNavController, bottomNavController)
            } else {
                ScreenContent()
            }
        }
    }

    @Composable
    protected abstract fun ScreenContent()

    @Composable
    protected abstract fun getViewModel(): V
}
