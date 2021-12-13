package com.domain

import androidx.lifecycle.LiveData

class GetTaskItemListUseCase (private val taskListRepository: TaskListRepository) {
    fun getItemList () : LiveData <List<TaskItem>>{
        return taskListRepository.getItemList()
    }
}