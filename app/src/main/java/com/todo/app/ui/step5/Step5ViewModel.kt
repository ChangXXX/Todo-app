package com.todo.app.ui.step5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.data.model.Todo
import com.todo.app.data.repository.remote.TodoRemoteRepository
import com.todo.app.ui.step4.Step4ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Step5ViewModel @Inject constructor(
    private val todoRemoteRepository: TodoRemoteRepository
) : ViewModel() {
    private val _eventflow = MutableSharedFlow<Step5Event>()
    val eventflow = _eventflow.asSharedFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            val items = todoRemoteRepository.getTodos().values.toList()

            if (!items.isNullOrEmpty()) {
                event(Step5Event.TodoList(items))
            }
        }
    }

    fun showToast() {
        event(Step5Event.ShowToast("TOAST"))
    }

    fun openAddFragment() {
        event(Step5Event.OpenAddFragment)
    }

    private fun event(event: Step5Event) {
        viewModelScope.launch {
            _eventflow.emit(event)
        }
    }


    sealed class Step5Event {
        data class TodoList(val todos: List<Todo>) : Step5Event()
        data class ShowToast(val text: String) : Step5Event()
        object OpenAddFragment : Step5Event()
    }
}