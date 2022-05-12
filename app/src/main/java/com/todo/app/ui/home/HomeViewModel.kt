package com.todo.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.todo.app.ui.common.Event
import com.todo.app.ui.common.MutableSingleLiveData
import com.todo.app.ui.common.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _openStepOneFragmentEvent = MutableLiveData<Event<Unit>>()
    val openStepOneFragmentEvent: LiveData<Event<Unit>> = _openStepOneFragmentEvent

    private val _openStepTwoFragmentEvent = MutableSingleLiveData<Unit>()
    val openStepTwoFragmentEvent: SingleLiveData<Unit> = _openStepTwoFragmentEvent

    private val _openStepThreeFragmentEvent = MutableSingleLiveData<Unit>()
    val openStepThreeFragmentEvent: SingleLiveData<Unit> = _openStepThreeFragmentEvent

    private val _openStepFourFragmentEvent = MutableSingleLiveData<Unit>()
    val openStepFourFragmentEvent: SingleLiveData<Unit> = _openStepFourFragmentEvent

    private val _openStepFiveFragmentEvent = MutableSingleLiveData<Unit>()
    val openStepFiveFragmentEvent: SingleLiveData<Unit> = _openStepFiveFragmentEvent

    private val _openStepSixFragmentEvent = MutableSingleLiveData<Unit>()
    val openStepSixFragmentEvent: SingleLiveData<Unit> = _openStepSixFragmentEvent

    fun openStepOneFragment() {
        _openStepOneFragmentEvent.value = Event(Unit)
    }

    fun openStepTwoFragment() = _openStepTwoFragmentEvent.setValue(Unit)
    fun openStepThreeFragment() = _openStepThreeFragmentEvent.setValue(Unit)
    fun openStepFourFragment() = _openStepFourFragmentEvent.setValue(Unit)
    fun openStepFiveFragment() = _openStepFiveFragmentEvent.setValue(Unit)
    fun openStepSixFragment() = _openStepSixFragmentEvent.setValue(Unit)
}