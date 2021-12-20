package com.presintation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.domain.TaskItem
import com.example.tasklist.R

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>(){

    val list = listOf<TaskItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_disabled, parent, true)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val taskItem = list[position]
        holder.tvName.text = taskItem.name
        holder.tvCount.text = taskItem.count.toString()
        holder.itemView.setOnLongClickListener {
            true
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TaskListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }
}