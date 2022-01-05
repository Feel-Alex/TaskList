package com.presintation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.domain.TaskItem
import com.example.tasklist.R
import java.lang.RuntimeException

class TaskListAdapter : ListAdapter<TaskItem, TaskListViewHolder> (TaskItemDiffCallback()){

    companion object {
        const val ENABLED = 1
        const val DISABLED = 0
    }

    val longClick : ((TaskItem) -> Unit)? = null
    val click : ((TaskItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val layout = when (viewType){
            ENABLED -> R.layout.item_task_enabled
            DISABLED -> R.layout.item_task_disabled
            else -> throw RuntimeException("Error in the oncreate view")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val viewHolderList = getItem(position)
        holder.tv_count.text = viewHolderList.count.toString()
        holder.tv_name.text = viewHolderList.name
        holder.view.setOnLongClickListener{
            longClick?.invoke(viewHolderList)
            true
        }
        holder.view.setOnClickListener {
            click?.invoke(viewHolderList)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewTipeList = getItem(position)
        return if (viewTipeList.enabled) ENABLED else DISABLED
    }
}
