package com.presintation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.domain.TaskItem
import com.example.tasklist.R
import java.lang.RuntimeException

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>(){

    var taskList = listOf<TaskItem>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }



    companion object {
        const val TYPE_ENABLED = 0
        const val TYPE_DISABLED = 1
    }

    var longClickListener : ((TaskItem) -> Unit?)? = null
    var clickListener : ((TaskItem) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val layout = when (viewType){
            TYPE_ENABLED -> R.layout.item_task_enabled
            TYPE_DISABLED -> R.layout.item_task_disabled
            else -> throw RuntimeException("Error")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val itemPosition = taskList[position]
        holder.tv_name.text = itemPosition.name
        holder.tv_count.text = itemPosition.count.toString()
        holder.view.setOnLongClickListener {
            longClickListener?.invoke(itemPosition)
            true
        }
        holder.view.setOnClickListener {
            clickListener?.invoke(itemPosition)
        }


    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun getItemViewType(position: Int): Int {
        val viewTipePosition = taskList[position]
        return if (viewTipePosition.enabled) TYPE_ENABLED else TYPE_DISABLED
    }

    class TaskListViewHolder(val view : View) : RecyclerView.ViewHolder (view){
        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        val tv_count = view.findViewById<TextView>(R.id.tv_count)

    }
}
