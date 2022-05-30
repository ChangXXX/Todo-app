package com.todo.app.ui.step6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.data.model.Todo
import com.todo.app.data.repository.remote.TodoRemoteRepository
import com.todo.app.ui.common.MutableEventFlow
import com.todo.app.ui.common.asEventFlow
import com.todo.app.ui.step5.Step5ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Step6ViewModel @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
): ViewModel() {
    private val _eventFlow = MutableEventFlow<Step6Event>()
    val eventFlow = _eventFlow.asEventFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            val items = todoRemoteRepository.getTodos().values.toList()

            if (!items.isNullOrEmpty()) {
                event(Step6Event.TodoList(items))
            }
        }
    }

    fun showToast() {
        event(Step6Event.ShowToast("TOAST"))
    }

    fun openAddFragment() {
        event(Step6Event.OpenAddFragment)
    }

    private fun event(event: Step6Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Step6Event {
        data class TodoList(val todos: List<Todo>) : Step6Event()
        data class ShowToast(val text: String) : Step6Event()
        object OpenAddFragment : Step6Event()
    }
}