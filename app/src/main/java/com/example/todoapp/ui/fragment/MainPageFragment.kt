package com.example.todoapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentMainPageBinding
import com.example.todoapp.ui.adapter.ToDosAdapter
import com.example.todoapp.ui.viewmodel.MainPageViewModel
import com.example.todoapp.utils.transition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewModel: MainPageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)

//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.toDosList.observe(viewLifecycleOwner) {
            val toDosAdapter = ToDosAdapter(requireContext(), it, viewModel)
            binding.recyclerView.adapter = toDosAdapter
        }

        binding.fab.setOnClickListener {
            Navigation.transition(it, R.id.toDoRecordFragment)
        }

        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                search(query)
                return false
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainPageViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun search(searchWord: String) {
        viewModel.search(searchWord)
    }

    override fun onResume() {
        super.onResume()
        viewModel.uploadToDo()
    }
}