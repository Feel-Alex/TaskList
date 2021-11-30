package com.domain

class GetTaskItemList (private val taskListRepository: TaskListRepository) {
    fun getItemList () : List<TaskItem>{
        return taskListRepository.getItemList()
    }
}