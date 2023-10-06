package com.example.todoapp.ui.fragment

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentToDoDetailBinding
import com.example.todoapp.ui.viewmodel.ToDoDetailViewModel
import com.example.todoapp.utils.transition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoDetailFragment : Fragment() {
    private lateinit var binding: FragmentToDoDetailBinding
    private lateinit var viewModel: ToDoDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoDetailBinding.inflate(inflater, container, false)

        val bundle: ToDoDetailFragmentArgs by navArgs()
        val toDoComing = bundle.toDo
        binding.editTextToDoName.setText(toDoComing.toDo_name)

        binding.buttonUpdate.setOnClickListener {
            val toDo_name = binding.editTextToDoName.text.toString()
            update(toDoComing.toDo_id, toDo_name)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ToDoDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun update(toDo_id: Int, toDo_name: String) {
        viewModel.update(toDo_id, toDo_name)
        findNavController().navigate(R.id.detailToMainPageTransition)
    }

}