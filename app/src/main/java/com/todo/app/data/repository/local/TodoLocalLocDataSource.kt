package com.todo.app.data.repository.local

import com.todo.app.data.database.TodoDao
import com.todo.app.data.model.Todo
import javax.inject.Inject

class TodoLocalLocDataSource @Inject constructor(private val todoDao: TodoDao) : TodoLocDataSource {

    override suspend fun addTodo(todo: Todo) {
        todoDao.insert(todo)
    }

    override suspend fun getTodos(): List<Todo> {
        return todoDao.load()
    }
}