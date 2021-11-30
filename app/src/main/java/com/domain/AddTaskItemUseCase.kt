package com.domain

class AddTaskItemUseCase(private val taskListRepository: TaskListRepository) {

    fun addTaskItem (taskItem: TaskItem){
        taskListRepository.addTaskItem(taskItem)
    }
}