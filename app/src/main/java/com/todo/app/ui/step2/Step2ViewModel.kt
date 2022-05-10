package com.todo.app.ui.step2

import androidx.lifecycle.ViewModel
import com.todo.app.common.MutableSingleLiveData
import com.todo.app.common.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Step2ViewModel @Inject constructor() : ViewModel() {

    private val _addTodoEvent = MutableSingleLiveData<Unit>()
    val addTodoEvent: SingleLiveData<Unit> = _addTodoEvent

    fun openAddTodoEvent() {
        _addTodoEvent.setValue(Unit)
    }
}