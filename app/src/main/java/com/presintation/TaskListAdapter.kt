package com.presintation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.domain.TaskItem
import com.example.tasklist.R

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {

    var taskList = listOf<TaskItem>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_disabled, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val listItem = taskList[position]
        val status = if (listItem.enabled) "Active" else "Not Active"

        holder.tv_count.text = listItem.count.toString()
        holder.tv_name.text = status
        holder.view.setOnLongClickListener {
            true
        }
        if (listItem.enabled)holder.tv_name.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.holo_orange_light))
    }

    override fun getItemCount(): Int {
        return taskList.size
    }



class TaskListViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val tv_name = view.findViewById<TextView>(R.id.tv_name)
    val tv_count = view.findViewById<TextView>(R.id.tv_count)

}
}