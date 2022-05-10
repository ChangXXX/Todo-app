package com.todo.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.todo.app.common.AssetLoader
import com.todo.app.data.database.AppDatabase
import com.todo.app.data.database.TodoDao
import com.todo.app.data.repository.asset.TodoAssetDataSource
import com.todo.app.data.repository.asset.TodoAssetRepository
import com.todo.app.data.repository.asset.TodoDataSource
import com.todo.app.data.repository.local.TodoLocalDataSource
import com.todo.app.data.repository.local.TodoLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideIoDispatcher() = Dispatchers.IO

    // Step 1
    @Provides
    @Singleton
    fun providesAssetLoader(@ApplicationContext appContext: Context): AssetLoader =
        AssetLoader(appContext)

    @Provides
    @Singleton
    fun provideTodoAssetDataSource(assetLoader: AssetLoader): TodoDataSource {
        return TodoAssetDataSource(assetLoader)
    }

    @Provides
    @Singleton
    fun provideTodoAssetRepository(assetDataSource: TodoAssetDataSource): TodoAssetRepository {
        return TodoAssetRepository(assetDataSource)
    }

    // Step 2
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Todos.db"
        ).build()
    }

    @Provides
    fun provideTodoDao(appDatabase: AppDatabase): TodoDao {
        return appDatabase.todoDao()
    }

    @Provides
    @Singleton
    fun providesTodoLocalDataSource(todoDao: TodoDao): com.todo.app.data.repository.local.TodoDataSource {
        return TodoLocalDataSource(todoDao)
    }

    @Provides
    @Singleton
    fun providesTodoLocalRepository(
        localDataSource: TodoLocalDataSource,
        ioDispatcher: CoroutineDispatcher
    ): TodoLocalRepository {
        return TodoLocalRepository(localDataSource, ioDispatcher)
    }


}