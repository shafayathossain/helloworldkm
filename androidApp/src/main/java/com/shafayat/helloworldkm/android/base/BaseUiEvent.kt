package com.shafayat.helloworldkm.android.base

import com.shafayat.helloworldkm.android.ui.components.text.UiText

sealed class BaseUiEvent {
    object PopBackStack : BaseUiEvent()
    data class Navigate(val route: String) : BaseUiEvent()
    data class ShowToast(val message: UiText) : BaseUiEvent()
    data class ShowSnackBar(
        val message: UiText,
        val action: UiText? = null,
    ) : BaseUiEvent()
}