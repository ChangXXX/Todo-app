package com.todo.app.data.repository.asset

import com.todo.app.data.model.TodoAssetData
import javax.inject.Inject

class TodoAssetRepository @Inject constructor(
    private val assetDataSource: TodoAssetDataSource
) {

    suspend fun getTodoAssetData(): TodoAssetData? {
        return assetDataSource.getTodoFromAsset()
    }
}