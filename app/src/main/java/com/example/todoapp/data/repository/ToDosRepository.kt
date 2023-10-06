package com.example.todoapp.data.repository

import com.example.todoapp.data.datasource.ToDosDataSource
import com.example.todoapp.data.entity.ToDos

class ToDosRepository(var tDDataSource: ToDosDataSource) {
    suspend fun save(toDo_name: String) = tDDataSource.save(toDo_name)

    suspend fun update(toDo_id: Int, toDo_name: String) = tDDataSource.update(toDo_id, toDo_name)

    suspend fun delete(toDo_id: Int) = tDDataSource.delete(toDo_id)

    suspend fun uploadToDo(): List<ToDos> = tDDataSource.uploadToDo()

    suspend fun search(searchWord: String): List<ToDos> = tDDataSource.search(searchWord)
}