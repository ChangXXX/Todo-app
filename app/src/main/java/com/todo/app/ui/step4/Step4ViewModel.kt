package com.todo.app.ui.step4

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
class Step4ViewModel @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
) : ViewModel() {

    private val _eventflow = MutableSharedFlow<Event>()
    val eventflow = _eventflow.asSharedFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            val items = todoRemoteRepository.getTodos().values.toList()

            if (!items.isNullOrEmpty()) {
                event(Event.TodoList(items))
            }
        }
    }

    fun showToast() {
        event(Event.ShowToast("TOAST"))
    }

    fun openAddFragment() {
        event(Event.OpenAddFragment)
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventflow.emit(event)
        }
    }

    sealed class Event {
        data class TodoList(val todos: List<Todo>) : Event()
        data class ShowToast(val text: String) : Event()
        object OpenAddFragment : Event()
    }
}