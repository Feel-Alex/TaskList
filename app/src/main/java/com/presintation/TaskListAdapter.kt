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

class TaskListAdapter : ListAdapter <TaskItem, TaskListViewHolder>(TaskItemDiffCallback()) {


    companion object {
        const val ENABLED = 1;
        const val DISABLED = 0;
    }

    var longClick: ((TaskItem) -> Unit)? = null
    var click: ((TaskItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val layout = when (viewType) {
            ENABLED -> R.layout.item_task_enabled
            DISABLED -> R.layout.item_task_disabled
            else -> throw RuntimeException("Error in the onCreateView")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TaskListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val bindList = getItem(position)
        holder.tv_count.text = bindList.name
        holder.tv_name.text = bindList.count.toString()
        holder.view.setOnClickListener {
            click?.invoke(bindList)
        }
        holder.view.setOnLongClickListener {
            longClick?.invoke(bindList)
            true
        }
    }


    override fun getItemViewType(position: Int): Int {
        val viewTipeList = getItem(position)
        return if (viewTipeList.enabled) ENABLED else DISABLED
    }


}
