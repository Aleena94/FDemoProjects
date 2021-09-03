package com.example.practicetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetest.R
import com.example.practicetest.databinding.ItemTodoBinding
import com.example.practicetest.interfaces.ItemClickListener
import com.example.practicetest.model.ToDoModelItem

class ToDoAdapter(
    musicList: List<ToDoModelItem>,
    clickListener: ItemClickListener
) : RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {
    private var todoList: List<ToDoModelItem>
    private lateinit var binding: ItemTodoBinding
    private val clickListener: ItemClickListener

    fun setToDoList(todoList: List<ToDoModelItem>) {
        this.todoList = todoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        todoList[position].apply {
            binding.tvTitle.text = title
            if (completed) {
                binding.imgCompleted.setImageResource(R.drawable.ic_completed)
            } else {
                binding.imgCompleted.setImageResource(R.drawable.ic_not_completed)
            }
            if (favourite) {
                binding.imgFavourite.setImageResource(R.drawable.ic_favourite)
            } else {
                binding.imgFavourite.setImageResource(R.drawable.ic_not_favourite)
            }
            binding.imgFavourite.setOnClickListener {
                if (favourite) {
                    clickListener.onItemClick(false, id, position)
                    binding.imgFavourite.setImageResource(R.drawable.ic_not_favourite)
                }
                else {
                    clickListener.onItemClick(true, id,position)
                    binding.imgFavourite.setImageResource(R.drawable.ic_favourite)
                }
            }
        }


    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class MyViewHolder(itemBinding: ItemTodoBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    init {
        this.todoList = musicList
        this.clickListener = clickListener

    }
}
