package com.todo.app.data.repository.remote

import com.todo.app.data.model.Todo
import com.todo.app.api.TodoService
import javax.inject.Inject

class TodoRemoteDataSource @Inject constructor(
    private val todoService: TodoService
) : TodoRemDataSource {
    override suspend fun addTodo(todo: Todo) {
        todoService.addTodo(todo)
    }

    override suspend fun getTodos(): Map<String, Todo> {
        return todoService.getTodos()
    }
}