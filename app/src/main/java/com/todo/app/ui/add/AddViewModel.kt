package com.todo.app.ui.add

import androidx.lifecycle.ViewModel
import com.todo.app.common.MutableSingleLiveData
import com.todo.app.common.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {
    val title = MutableSingleLiveData<String>()
    val content = MutableSingleLiveData<String>()

    private val _addTodoEvent = MutableSingleLiveData<Unit>()
    val addTodoEvent: SingleLiveData<Unit> = _addTodoEvent


}