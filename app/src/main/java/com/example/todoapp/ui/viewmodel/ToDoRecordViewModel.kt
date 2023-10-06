package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repository.ToDosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoRecordViewModel @Inject constructor(var tDRepo: ToDosRepository) : ViewModel() {

    fun save(toDo_name: String) {
        CoroutineScope(Dispatchers.Main).launch {
            tDRepo.save(toDo_name)
        }
    }
}