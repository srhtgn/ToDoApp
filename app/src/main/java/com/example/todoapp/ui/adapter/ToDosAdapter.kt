package com.example.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.entity.ToDos
import com.example.todoapp.databinding.CardDesignBinding
import com.example.todoapp.ui.fragment.MainPageFragmentDirections
import com.example.todoapp.ui.viewmodel.MainPageViewModel
import com.example.todoapp.utils.transition
import com.google.android.material.snackbar.Snackbar
import java.nio.file.Files.delete

class ToDosAdapter(
    var mContext: Context,
    var toDosList: List<ToDos>,
    var viewModel: MainPageViewModel
) : RecyclerView.Adapter<ToDosAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var design: CardDesignBinding) :
        RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val toDo = toDosList.get(position)
        val t = holder.design
        t.textViewToDoName.text = toDo.toDo_name

        t.imageViewDelete.setOnClickListener {
            Snackbar.make(it, "${toDo.toDo_name} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET") {
                    delete(toDo.toDo_id)
                }.show()
        }

        t.cardViewLine.setOnClickListener {
            val transition = MainPageFragmentDirections.toDoDetailTransition(toDo = toDo)
            Navigation.transition(it, transition)
        }
    }

    override fun getItemCount(): Int {
        return toDosList.size
    }

    fun delete(toDo_id: Int) {
        viewModel.delete(toDo_id)
    }
}