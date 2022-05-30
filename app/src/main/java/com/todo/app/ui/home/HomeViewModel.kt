package com.todo.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.ui.common.Event
import com.todo.app.ui.common.MutableSingleLiveData
import com.todo.app.ui.common.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _openStepOneFragmentEvent = MutableLiveData<Event<Unit>>()
    val openStepOneFragmentEvent: LiveData<Event<Unit>> = _openStepOneFragmentEvent

    private val _openStepTwoFragmentEvent = MutableSingleLiveData<Unit>()
    val openStepTwoFragmentEvent: SingleLiveData<Unit> = _openStepTwoFragmentEvent

    private val _openStepThreeFragmentEvent = MutableSharedFlow<Unit>()
    val openStepThreeFragmentEvent = _openStepThreeFragmentEvent.asSharedFlow()

    private val _eventFlow = MutableSharedFlow<OpenFragmentEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun openStepOneFragment() {
        _openStepOneFragmentEvent.value = Event(Unit)
    }

    fun openStepTwoFragment() = _openStepTwoFragmentEvent.setValue(Unit)

    fun openStepThreeFragment() {
        viewModelScope.launch {
            _openStepThreeFragmentEvent.emit(Unit)
        }
    }

    fun openFour() {
        openFragmentEvent(OpenFragmentEvent.StepFour)
    }

    fun openFive() {
        openFragmentEvent(OpenFragmentEvent.StepFive)
    }

    fun openSix() {
        openFragmentEvent(OpenFragmentEvent.StepSix)
    }

    private fun openFragmentEvent(event: OpenFragmentEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class OpenFragmentEvent {
        object StepFour : OpenFragmentEvent()
        object StepFive : OpenFragmentEvent()
        object StepSix : OpenFragmentEvent()
    }
}