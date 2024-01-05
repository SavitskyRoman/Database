package com.example.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChangeCardViewModelFactory (private val taskId: Long, private val dao: TaskDao)
    : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangeCardViewModel::class.java)) {
            return ChangeCardViewModel(taskId, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}