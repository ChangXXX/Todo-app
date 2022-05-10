package com.todo.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "contents") val contents: String
)
