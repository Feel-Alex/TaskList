package com.presintation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.data.TaskListRepositoryImpl
import com.domain.*
import java.lang.Exception

class TaskItemViewModel : ViewModel() {

    val repository = TaskListRepositoryImpl

    val addTaskItemUseCase = AddTaskItemUseCase(repository)
    val getTaskItemUseCase = GetTaskItemUseCase(repository)
    val editTaskItemUseCase = EditTaskItemUseCase(repository)

    private var _inputNameError = MutableLiveData<Boolean>() // Добавляем в LiveData ошибку имени
    val inputNameError: LiveData<Boolean>
        get() = _inputNameError

    private var _inputCountError =
        MutableLiveData<Boolean>() // Добавляем в LiveData ошибку подсчета
    val inputCountError: LiveData<Boolean>
        get() = _inputCountError

    private var _getTaskItemLD = MutableLiveData<TaskItem>() // Добавляем в LiveData TaskItem
    val getTaskItemLD: LiveData<TaskItem>
        get() = _getTaskItemLD

    private var _uiIsClose = MutableLiveData<Unit>() // Оповещаем о закрытии экрана
    val uiIsClose: LiveData<Unit>
        get() = _uiIsClose


    fun addTaskItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val validData = inputValidData(name, count)
        if (validData) {
            val taskItem = TaskItem(name, count, true)
            addTaskItemUseCase.addTaskItem(taskItem)
        }
        finishWork()
    }

    fun getTaskItem(taskItemId: Int) {
        val item = getTaskItemUseCase.getTaskItem(taskItemId)
        _getTaskItemLD.value = item
    }

    fun editTaskItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val validData = inputValidData(name, count)
        if (validData) {
            _getTaskItemLD.value?.let {
                val taskItemCopy = it.copy(name, count)
            editTaskItemUseCase.editTaskItem(taskItemCopy)
            }
        }
        finishWork()
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun inputValidData(inputName: String, inputCount: Int): Boolean {
        var result = true
        if (inputName.isBlank()) {
            _inputNameError.value = true
            result = false
        }
        if (inputCount <= 0) {
            _inputCountError.value = true
            result = false
        }
        return result
    }

    fun resetInputName() {
        _inputNameError.value = false
    }

    fun resetInputCount() {
        _inputCountError.value = false
    }

    fun finishWork() {
        _uiIsClose.value = Unit
    }

}