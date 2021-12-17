package com.presintation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.domain.TaskItem
import com.example.tasklist.R
import com.example.tasklist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.taskList.observe(this){
            Log.d("myne", it.toString())
            loadLinear(it)
        }
    }


    fun loadLinear (list: List<TaskItem>){
        binding.llMainSV.removeAllViews()
        for (listItem in list) {
            val taskItemId = if (listItem.enabled) R.layout.item_task_enabled else R.layout.item_task_disabled
            val view = LayoutInflater.from(this).inflate(taskItemId, binding.llMainSV, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvCount.text = listItem.count.toString()
            tvName.text = listItem.name
            view.setOnLongClickListener {
                viewModel.changeEnabledState(listItem)
                true
            }

            binding.llMainSV.addView(view)

        }
    }
}