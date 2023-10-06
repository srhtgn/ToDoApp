package com.example.todoapp.data.datasource

import com.example.todoapp.data.entity.ToDos
import com.example.todoapp.room.ToDosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ToDosDataSource(var tDDao: ToDosDao) {
    suspend fun save(toDo_name: String) {
        val newToDo = ToDos(0, toDo_name)
        tDDao.save(newToDo)
    }

    suspend fun update(toDo_id: Int, toDo_name: String) {
        val updatedToDo = ToDos(toDo_id, toDo_name)
        tDDao.update(updatedToDo)
    }

    suspend fun delete(toDo_id: Int) {
        val deletedToDo = ToDos(toDo_id, "")
        tDDao.delete(deletedToDo)
    }

    suspend fun uploadToDo(): List<ToDos> = withContext(Dispatchers.IO) {
        return@withContext tDDao.uploadToDo()
    }

    suspend fun search(searchWord: String): List<ToDos> = withContext(Dispatchers.IO) {
        return@withContext tDDao.search(searchWord)
    }

}