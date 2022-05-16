package com.todo.app.data.repository.remote

import com.todo.app.data.model.Todo
import javax.inject.Inject

class TodoRemoteRepository @Inject constructor(
    private val todoRemoteDataSource: TodoRemoteDataSource
) {

    suspend fun addTodo(todo: Todo) {
        todoRemoteDataSource.addTodo(todo)
    }

    suspend fun getTodos(): Map<String, Todo> {
        return todoRemoteDataSource.getTodos()
    }
}