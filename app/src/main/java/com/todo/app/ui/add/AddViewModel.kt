package com.todo.app.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.common.MutableSingleLiveData
import com.todo.app.common.SingleLiveData
import com.todo.app.data.model.Todo
import com.todo.app.data.repository.local.TodoLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val todoLocalRepository: TodoLocalRepository
) : ViewModel() {

    private val _title = MutableSingleLiveData<String>()
    val title: SingleLiveData<String> = _title

    private val _contents = MutableSingleLiveData<String>()
    val contents: SingleLiveData<String> = _contents

    private val _addTodoEvent = MutableSingleLiveData<Unit>()
    val addTodoEvent: SingleLiveData<Unit> = _addTodoEvent

    fun addAddTodoEvent() = _addTodoEvent.setValue(Unit)

    fun addTodo(todoItem: Todo) {
        viewModelScope.launch {
            todoLocalRepository.addTodo(todoItem)
        }
    }
}