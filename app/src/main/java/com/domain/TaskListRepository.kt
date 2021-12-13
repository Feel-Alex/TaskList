package com.domain

import androidx.lifecycle.LiveData

interface TaskListRepository {
    fun addTaskItem (taskItem: TaskItem)
    fun deleteTaskItem (taskItem: TaskItem)
    fun editTaskItem(taskItem: TaskItem)
    fun getTaskItem (taskItemId : Int) : TaskItem
    fun getItemList () : LiveData<List<TaskItem>>
}