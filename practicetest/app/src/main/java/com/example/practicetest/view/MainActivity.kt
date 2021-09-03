package com.example.practicetest.view

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicetest.R
import com.example.practicetest.adapter.ToDoAdapter
import com.example.practicetest.databinding.ActivityMainBinding
import com.example.practicetest.model.ToDoModelItem
import com.example.practicetest.services.isOnline
import com.example.practicetest.viewmodel.ToDoViewModel
import com.paginate.Paginate
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetest.interfaces.ItemClickListener
import com.paginate.abslistview.LoadingListItemCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.lang.String
import java.util.*


class MainActivity : AppCompatActivity(), ItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ToDoAdapter
    private val toDoViewModel: ToDoViewModel by inject()
    private var toDoItem: List<ToDoModelItem> = ArrayList<ToDoModelItem>()
    private var loading = false
    private var page = 1
    private var handler: Handler? = null
    private var paginate: Paginate? = null
    private var totalPages = 1
    private var networkDelay: Long = 2000
    val PREFS_NAME = "MyPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@MainActivity

        layoutManager = LinearLayoutManager(this)
        handler = Handler()
        adapter = ToDoAdapter(toDoItem,context as MainActivity)

        var prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val isFirstRun=prefs.getBoolean("firstRun", true)
        if (isFirstRun) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (isOnline(context)) {
                    loadToDo()
                }
            }
            editor.putBoolean("firstRun", false)
            editor.apply()
            editor.commit()
        } else {

            toDoViewModel.getToDoDetails()!!.observe(this,{
                if (it != null) {
                    toDoItem = it

                    binding.todoRecycler.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.todoRecycler.adapter = adapter
                    adapter.setToDoList(toDoItem)

                }
            })
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadToDo() {
        toDoViewModel.getToDo()!!.observe(this, { toDoList ->

                    if (toDoList != null) {
                        toDoItem = toDoList
                        lifecycleScope.launch {
                            toDoViewModel.insertToDoData(toDoItem)
                        }
                        binding.todoRecycler.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        binding.todoRecycler.adapter = adapter
                        adapter.setToDoList(toDoItem)
            }

        })


    }

    override fun onItemClick(favourite: Boolean, id: Int, position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            toDoViewModel.updateFavourites(favourite,id)
        }
        adapter.notifyItemChanged(position)
    }
}