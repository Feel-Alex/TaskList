package com.presintation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.data.TaskListRepositoryImpl
import com.domain.*

class MainViewModel : ViewModel() {

    val repository = TaskListRepositoryImpl

    val getTaskItemListUseCase = GetTaskItemListUseCase(repository);
    val deleteTaskItemUseCase = DeleteTaskItemUseCase(repository)
    val editTaskItemUseCase = EditTaskItemUseCase(repository)

    val taskList = MutableLiveData<List<TaskItem>>()

    fun getTaskList (){
        val list = getTaskItemListUseCase.getItemList()
        taskList.value = list
    }


    fun deleteTaskItem (taskItem: TaskItem){
        deleteTaskItemUseCase.deleteTaskItem(taskItem)
        getTaskList()
    }

    fun changeEnabledState (taskItem: TaskItem){
        val newItem = taskItem.copy(enabled = !taskItem.enabled)
        editTaskItemUseCase.editTaskItem(newItem)
        getTaskList()
    }





}