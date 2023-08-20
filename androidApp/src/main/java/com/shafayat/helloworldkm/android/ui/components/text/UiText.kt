package com.shafayat.helloworldkm.android.ui.components.text

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class StaticText(val text: String) : UiText()
    data class DynamicText(@StringRes val resId: Int) : UiText()

    fun extract(context: Context? = null): String {
        return when (this) {
            is StaticText -> this.text
            is DynamicText -> context?.getString(this.resId) ?: ""
        }
    }
}