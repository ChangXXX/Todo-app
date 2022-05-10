package com.todo.app.di

import android.content.Context
import com.todo.app.common.AssetLoader
import com.todo.app.repository.TodoAssetDataSource
import com.todo.app.repository.TodoAssetRepository
import com.todo.app.repository.TodoDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
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



}