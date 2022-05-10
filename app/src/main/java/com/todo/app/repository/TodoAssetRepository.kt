package com.todo.app.repository

import com.todo.app.data.TodoAssetData
import javax.inject.Inject

class TodoAssetRepository @Inject constructor(
    private val assetDataSource: TodoAssetDataSource
) {

    suspend fun getTodoAssetData(): TodoAssetData? {
        return assetDataSource.getTodoFromAsset()
    }
}