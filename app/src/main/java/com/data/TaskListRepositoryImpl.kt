package com.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.domain.TaskItem
import com.domain.TaskListRepository
import java.util.Comparator
import kotlin.random.Random

object TaskListRepositoryImpl : TaskListRepository {


    val liveDataTLR = MutableLiveData<List<TaskItem>>()
    private val taskListRepositoryImpl =
        sortedSetOf(Comparator<TaskItem> { p0, p1 -> p0.id.compareTo(p1.id) })

    private var autoincrementId = 0


    init {
        for (i in 0 until 100) {
            addTaskItem(
                TaskItem(
                    "name $i",
                    i,
                    Random.nextBoolean()
                )
            )
        }
    }


    override fun addTaskItem(taskItem: TaskItem) {
        if (taskItem.id == TaskItem.UNDEFINED_ID)
            taskItem.id = autoincrementId++
        taskListRepositoryImpl.add(taskItem)
        updateList()
    }

    override fun deleteTaskItem(taskItem: TaskItem) {
        taskListRepositoryImpl.remove(taskItem)
        updateList()
    }

    override fun editTaskItem(taskItem: TaskItem) {
        val oldElement = getTaskItem(taskItem.id)
        taskListRepositoryImpl.remove(oldElement)
        addTaskItem(taskItem)

    }

    override fun getTaskItem(taskItemId: Int): TaskItem {
        return taskListRepositoryImpl.find {
            it.id == taskItemId
        } ?: throw RuntimeException("Element with id $taskItemId not found")
    }

    override fun getItemList(): LiveData<List<TaskItem>> {
        return liveDataTLR
    }

    fun updateList() {
        liveDataTLR.value = taskListRepositoryImpl.toList()
    }

}