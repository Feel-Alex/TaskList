package com.presintation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.domain.TaskItem
import com.example.tasklist.R
import com.example.tasklist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var adapter: TaskListAdapter
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.taskList.observe(this) {
            adapter.submitList(it) }
        loadRecycler()
    }

    fun loadRecycler() {
        adapter = TaskListAdapter()
        binding.rvTaskList.adapter = adapter
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val swipeList = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteTaskItem(swipeList)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(findViewById(R.id.rv_task_list))

        adapter.longClick = {
            viewModel.changeEnabledState(it)
        }

        adapter.click = {
            Toast.makeText(this, "The bottom ${it.name} is pushed", Toast.LENGTH_SHORT).show()
        }

        viewModel.toast = {
            Toast.makeText(this, "The bottom ${it.name} is deleted ", Toast.LENGTH_SHORT).show()
        }

    }


}
