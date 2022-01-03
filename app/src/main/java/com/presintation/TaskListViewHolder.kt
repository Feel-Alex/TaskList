package com.presintation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklist.R

class TaskListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tv_name = view.findViewById<TextView>(R.id.tv_name)
    val tv_count = view.findViewById<TextView>(R.id.tv_count)
}