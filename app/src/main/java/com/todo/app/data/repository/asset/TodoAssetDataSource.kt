package com.todo.app.data.repository.asset

import com.google.gson.Gson
import com.todo.app.common.AssetLoader
import com.todo.app.data.model.TodoAssetData
import javax.inject.Inject

class TodoAssetDataSource @Inject constructor(private val assetLoader: AssetLoader) :
    TodoDataSource {
    private val gson = Gson()

    override suspend fun getTodoFromAsset(): TodoAssetData? {
        return assetLoader.getJsonString("todo.json")?.let { todoJsonString ->
            gson.fromJson(todoJsonString, TodoAssetData::class.java)
        }
    }
}