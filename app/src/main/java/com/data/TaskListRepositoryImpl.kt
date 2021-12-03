package com.data

import com.domain.TaskItem
import com.domain.TaskListRepository

object TaskListRepositoryImpl : TaskListRepository {

    private val taskListRepositoryImpl = mutableListOf<TaskItem>()
    private var autoincrementId = 0


    override fun addTaskItem(taskItem: TaskItem) {
        if (taskItem.id == TaskItem.UNDEFINED_ID)
        taskItem.id = autoincrementId ++
        taskListRepositoryImpl.add(taskItem)
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskListRepositoryImpl.remove(taskItem)
    }

    override fun editTaskItem(taskItem: TaskItem) {
        val oldElement  = getTaskItem(taskItem.id)
        taskListRepositoryImpl.remove(oldElement)
        addTaskItem(taskItem)

    }

    override fun getTaskItem(taskItemId: Int): TaskItem {
        return taskListRepositoryImpl.find {
            it.id == taskItemId
        } ?: throw RuntimeException ("Element with id $taskItemId not found")
    }

    override fun getItemList(): List<TaskItem> {
        return taskListRepositoryImpl.toList()
    }
}