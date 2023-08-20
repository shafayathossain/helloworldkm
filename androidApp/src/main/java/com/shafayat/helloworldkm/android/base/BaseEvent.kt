package com.shafayat.helloworldkm.android.base

open class BaseEvent {
    object OnShowLoadingDialog : BaseEvent()
    object OnDisMissLoadingDialog : BaseEvent()
    object OnShowErrorDialog : BaseEvent()
    object OnDisMissErrorDialog : BaseEvent()
}