package com.shafayat.helloworldkm.android

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    init {
        //do some network call
        viewModelScope.launch {
            delay(1000)
            isLoading = false
        }
    }
}