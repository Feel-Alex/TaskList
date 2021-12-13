package com.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.TaskItem
import com.domain.TaskListRepository

object TaskListRepositoryImpl : TaskListRepository {


    lateinit var liveDataTLR : MutableLiveData<List<TaskItem>>
    private val taskListRepositoryImpl = mutableListOf<TaskItem>()
    private var autoincrementId = 0


    init {
        for (i in 0 until 10){
            addTaskItem(TaskItem("name $i", i, true))
        }
    }


    override fun addTaskItem(taskItem: TaskItem) {
        if (taskItem.id == TaskItem.UNDEFINED_ID)
        taskItem.id = autoincrementId ++
        taskListRepositoryImpl.add(taskItem)
        updateList ()
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskListRepositoryImpl.remove(taskItem)
        updateList ()
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

    override fun getItemList() : LiveData<List<TaskItem>> {
        return liveDataTLR
    }

    fun updateList (){
        liveDataTLR.value = taskListRepositoryImpl.toList()
    }

}