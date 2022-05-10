package com.todo.app.repository

import com.todo.app.data.TodoAssetData

interface TodoDataSource {

    suspend fun getTodoFromAsset(): TodoAssetData?
}