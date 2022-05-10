package com.todo.app.data.repository.local

import com.todo.app.data.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoLocalRepository @Inject constructor(
    private val localDataSource: TodoLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addTodo(todo: Todo) {
        withContext(ioDispatcher) {
            localDataSource.addTodo(todo)
        }
    }

    suspend fun getTodoItems(): List<Todo> {
        return withContext(ioDispatcher) {
            localDataSource.getTodos()
        }
    }
}