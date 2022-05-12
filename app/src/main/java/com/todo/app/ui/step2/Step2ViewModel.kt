package com.todo.app.ui.step2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.ui.common.MutableSingleLiveData
import com.todo.app.ui.common.SingleLiveData
import com.todo.app.data.model.Todo
import com.todo.app.data.repository.local.TodoLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Step2ViewModel @Inject constructor(
    private val todoLocalRepository: TodoLocalRepository
) : ViewModel() {

    private val _addTodoEvent = MutableSingleLiveData<Unit>()
    val addTodoEvent: SingleLiveData<Unit> = _addTodoEvent

    private val _todos = MutableSingleLiveData<List<Todo>>()
    val todos: SingleLiveData<List<Todo>> = _todos

    init {
        loadTodos()
    }

    fun openAddTodoEvent() {
        _addTodoEvent.setValue(Unit)
    }

    fun loadTodos() {
        viewModelScope.launch {
            val items = todoLocalRepository.getTodoItems()
            if (!items.isNullOrEmpty()) {
                _todos.setValue(items)
            }
        }
    }
}