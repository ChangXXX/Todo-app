package com.todo.app.data.repository.asset

import com.todo.app.data.model.TodoAssetData

interface TodoDataSource {

    suspend fun getTodoFromAsset(): TodoAssetData?
}