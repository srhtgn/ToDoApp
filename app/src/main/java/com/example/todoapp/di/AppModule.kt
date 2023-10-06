package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.datasource.ToDosDataSource
import com.example.todoapp.data.repository.ToDosRepository
import com.example.todoapp.room.Database
import com.example.todoapp.room.ToDosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideToDosRepository(tDds: ToDosDataSource): ToDosRepository {
        return ToDosRepository(tDds)
    }

    @Provides
    @Singleton
    fun provideToDosDataSource(tDDao: ToDosDao): ToDosDataSource {
        return ToDosDataSource(tDDao)
    }

    @Provides
    @Singleton
    fun provideTodosDao(@ApplicationContext context: Context): ToDosDao {
        val database = Room.databaseBuilder(context, Database::class.java, "toDo.sqlite")
            .createFromAsset("toDo.sqlite").build()
        return database.getToDosDao()
    }
}