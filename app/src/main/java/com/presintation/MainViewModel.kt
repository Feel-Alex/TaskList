package com.presintation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.data.TaskListRepositoryImpl
import com.domain.*

class MainViewModel : ViewModel() {

    private val repository = TaskListRepositoryImpl

    private val getTaskItemListUseCase = GetTaskItemListUseCase(repository);
    private val deleteTaskItemUseCase = DeleteTaskItemUseCase(repository)
    private val editTaskItemUseCase = EditTaskItemUseCase(repository)

    val taskList = getTaskItemListUseCase.getItemList()
    var toast : ((TaskItem) -> Unit)? = null

    fun deleteTaskItem (taskItem: TaskItem){
        toast?.invoke(taskItem)
         deleteTaskItemUseCase.deleteTaskItem(taskItem)
    }

    fun changeEnabledState (taskItem: TaskItem){
        val newItem = taskItem.copy(enabled = !taskItem.enabled)
        editTaskItemUseCase.editTaskItem(newItem)
    }

}