package com.example.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuizViewModelFactory (private val unitName: String, private val dao: TaskDao)
    : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
                return QuizViewModel(unitName, dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }