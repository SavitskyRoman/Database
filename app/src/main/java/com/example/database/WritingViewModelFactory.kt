package com.example.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WritingViewModelFactory(
    private val firstArrayCloneForWritting: Array<String>,
    private val secondArrayCloneForWritting: Array<String>,
    private val unitName: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WritingViewModel::class.java)) {
            return WritingViewModel(firstArrayCloneForWritting, secondArrayCloneForWritting, unitName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}