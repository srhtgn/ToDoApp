package com.example.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentToDoRecordBinding
import com.example.todoapp.ui.viewmodel.ToDoRecordViewModel
import com.example.todoapp.utils.transition
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ToDoRecordFragment : Fragment() {
    private lateinit var binding: FragmentToDoRecordBinding
    private lateinit var viewModel: ToDoRecordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoRecordBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener {
            val toDo_name = binding.editTextTodoName.text.toString()
            save(toDo_name)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ToDoRecordViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun save(toDo_name: String) {
        viewModel.save(toDo_name)
        findNavController().navigate(R.id.recordToMainPageTransition)
    }

}