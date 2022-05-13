package com.todo.app.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.ui.common.MutableSingleLiveData
import com.todo.app.ui.common.SingleLiveData
import com.todo.app.data.model.Todo
import com.todo.app.data.repository.local.TodoLocalRepository
import com.todo.app.data.repository.remote.TodoRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val todoLocalRepository: TodoLocalRepository,
    private val todoRemoteRepository: TodoRemoteRepository
) : ViewModel() {

    private val _addTodoEvent = MutableSingleLiveData<Unit>()
    val addTodoEvent: SingleLiveData<Unit> = _addTodoEvent

    fun addAddTodoEvent() = _addTodoEvent.setValue(Unit)

    fun addTodo(todoItem: Todo, roomIsChecked: Boolean) {
        viewModelScope.launch {
            if(roomIsChecked) todoLocalRepository.addTodo(todoItem)
            else todoRemoteRepository.addTodo(todoItem)
        }
    }
}