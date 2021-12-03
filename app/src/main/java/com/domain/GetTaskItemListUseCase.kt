package com.domain

class GetTaskItemListUseCase (private val taskListRepository: TaskListRepository) {
    fun getItemList () : List<TaskItem>{
        return taskListRepository.getItemList()
    }
}