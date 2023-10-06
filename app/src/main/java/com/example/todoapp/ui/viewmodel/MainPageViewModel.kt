package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.ToDos
import com.example.todoapp.data.repository.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainPageViewModel @Inject constructor(var tDRepo: ToDosRepository) : ViewModel() {
    var toDosList = MutableLiveData<List<ToDos>>()

    init {
        uploadToDo()
    }

    fun delete(toDo_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            tDRepo.delete(toDo_id)
            uploadToDo()
        }
    }

    fun uploadToDo() {
        CoroutineScope(Dispatchers.Main).launch {
            toDosList.value = tDRepo.uploadToDo()
        }
    }

    fun search(searchWord: String) {
        CoroutineScope(Dispatchers.Main).launch {
            toDosList.value = tDRepo.search(searchWord)
        }
    }
}