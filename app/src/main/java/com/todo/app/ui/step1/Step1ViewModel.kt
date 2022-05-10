package com.todo.app.ui.step1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.app.common.Event
import com.todo.app.data.Todo
import com.todo.app.repository.TodoAssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Step1ViewModel @Inject constructor(
    private val todoAssetRepository: TodoAssetRepository
) : ViewModel() {

    private val _addTodoEvent = MutableLiveData<Event<Unit>>()
    val addTodoEvent: LiveData<Event<Unit>> = _addTodoEvent

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        loadStep1TodoAssetData()
    }

    private fun loadStep1TodoAssetData() {
        viewModelScope.launch {
            val assetData = todoAssetRepository.getTodoAssetData()
            assetData?.let { todoAssetData ->
                _todos.value = todoAssetData.todos
            }
        }
    }

    fun openAddTodoEvent() {
        _addTodoEvent.value = Event(Unit)
    }
}