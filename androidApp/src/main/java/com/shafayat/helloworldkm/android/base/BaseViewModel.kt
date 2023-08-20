package com.shafayat.helloworldkm.android.base


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : BaseEvent> : ViewModel() {

    abstract fun onEvent(event: E)

    var baseState by mutableStateOf(BaseUiState())
        protected set

    fun onBaseEvent(event: BaseEvent) {
        when (event) {
            BaseEvent.OnDisMissLoadingDialog -> {
                baseState = baseState.copy(
                    isVisibleLoadingDialog = false
                )
            }

            BaseEvent.OnShowLoadingDialog -> {
                baseState = baseState.copy(
                    isVisibleLoadingDialog = true
                )
            }

            BaseEvent.OnDisMissErrorDialog -> {
                baseState = baseState.copy(
                    isVisibleErrorDialog = false
                )
            }

            BaseEvent.OnShowErrorDialog -> {
                baseState = baseState.copy(
                    isVisibleErrorDialog = true
                )
            }
        }
    }

    private val _baseUiEvent = Channel<BaseUiEvent>()
    val baseUiEvent = _baseUiEvent.receiveAsFlow()

    protected fun sendBaseUiEvent(event: BaseUiEvent) {
        viewModelScope.launch {
            _baseUiEvent.send(event)
        }
    }
}