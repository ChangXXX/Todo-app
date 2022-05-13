package com.todo.app.ui.step3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.data.model.Todo
import com.todo.app.data.repository.remote.TodoRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Step3ViewModel @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
) : ViewModel() {

    private val _addTodoEvent = MutableSharedFlow<Unit>()
    val addTodoEvent = _addTodoEvent.asSharedFlow()

    private val _todos = MutableSharedFlow<List<Todo>>()
    val todos = _todos.asSharedFlow()

    init {
        loadTodos()
    }

    fun openAddTodoEvent() {
        viewModelScope.launch {
            _addTodoEvent.emit(Unit)
        }
    }

    fun loadTodos() {
        viewModelScope.launch {
            val items = todoRemoteRepository.getTodos()
            if (!items.isNullOrEmpty()) {
                _todos.emit(items)
            }
        }
    }
}