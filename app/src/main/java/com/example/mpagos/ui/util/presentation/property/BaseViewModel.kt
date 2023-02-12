package com.example.mpagos.ui.util.presentation.property

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State, Action>(initialState: State) : ViewModel() {
    protected var _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    private val _action = Channel<Action?>(Channel.BUFFERED)
    val uiState: StateFlow<State> = _uiState.asStateFlow()
    val action = _action.receiveAsFlow()

    protected fun Action.send() {
        _action.trySendBlocking(this)
    }
}