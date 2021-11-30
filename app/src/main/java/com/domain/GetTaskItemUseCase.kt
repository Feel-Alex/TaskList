package com.domain

class GetTaskItemUseCase (private val taskListRepository: TaskListRepository) {
    fun getTaskItem (taskItemId : Int) : TaskItem{
       return taskListRepository.getTaskItem(taskItemId)
    }
}