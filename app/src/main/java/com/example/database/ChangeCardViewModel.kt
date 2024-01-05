package com.example.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChangeCardViewModel(val taskId: Long, val dao: TaskDao): ViewModel() {
    val task = dao.get(taskId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList
    fun updateTask() {
        viewModelScope.launch {
            dao.update(task.value!!)
            _navigateToList.value = true
        }
    }
    fun deleteTask() {
        viewModelScope.launch {
            dao.delete(task.value!!)
            _navigateToList.value = true
        }
    }
    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}
//<LinearLayout
//android:id="@+id/layout"
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:orientation="vertical"
//
//android:background="@color/task_layout">
//<androidx.cardview.widget.CardView
//android:id="@+id/card_view"
//android:layout_width="match_parent"
//android:layout_height="0dp"
//
//android:layout_margin="18dp"
//android:layout_weight="3"
//app:cardCornerRadius="18dp"
//app:cardElevation="18dp">
//
//<LinearLayout
//android:layout_width="match_parent"
//android:layout_height="match_parent"
//android:orientation="vertical">
//
//
//<TextView
//android:id="@+id/english_word"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginStart="8dp"
//android:layout_marginEnd="8dp"
//android:layout_marginTop="16dp"
//android:gravity="center"
//
//android:text="@{viewModel.englishWordOfSecret}"
//android:textSize="24sp"
//android:textColor="@color/task_text"
///>
//<TextView
//android:id="@+id/transcription_word"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginStart="8dp"
//android:layout_marginEnd="8dp"
//android:layout_marginBottom="8dp"
//
//android:gravity="center"
//android:text="@{viewModel.transcriptionWordOfSecret}"
//android:textSize="16sp"
//android:textColor="@color/task_text"
///>
//
//<TextView
//android:id="@+id/first_case"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginBottom="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginTop="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginEnd="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:layout_marginStart="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//
//android:minHeight="@dimen/min_height_for_text_view_case_1_4"
//android:background="@drawable/bg_text_view_case_1_4"
//android:gravity="center_vertical"
//android:paddingStart="@dimen/pading_start_for_text_view_case_1_4"
//
//android:text="@{viewModel.firstCase}"
//android:textColor="@color/task_text"
//android:textSize="@dimen/text_size_text_view_case_1_4"
//
///>
//
//<TextView
//android:textColor="@color/task_text"
//android:id="@+id/second_case"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginBottom="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginTop="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginEnd="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:layout_marginStart="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:minHeight="@dimen/min_height_for_text_view_case_1_4"
//android:background="@drawable/bg_text_view_case_1_4"
//android:gravity="center_vertical"
//android:paddingStart="@dimen/pading_start_for_text_view_case_1_4"
//
//android:text="@{viewModel.secondCase}"
//android:textSize="@dimen/text_size_text_view_case_1_4"
///>
//
//<TextView
//android:id="@+id/third_case"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//android:layout_marginBottom="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginTop="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginEnd="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:layout_marginStart="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:minHeight="@dimen/min_height_for_text_view_case_1_4"
//android:background="@drawable/bg_text_view_case_1_4"
//android:gravity="center_vertical"
//
//android:paddingStart="@dimen/pading_start_for_text_view_case_1_4"
//
//android:text="@{viewModel.thirdCase}"
//android:textSize="@dimen/text_size_text_view_case_1_4"
//android:textColor="@color/task_text"
///>
//
//<TextView
//android:id="@+id/fourth_case"
//android:layout_width="match_parent"
//android:layout_height="wrap_content"
//
//android:layout_marginBottom="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginTop="@dimen/layout_marginBottom_and_marginTop_for_text_view_case_1_4"
//android:layout_marginEnd="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:layout_marginStart="@dimen/layout_marginStart_and_marginEnd_for_text_view_case_1_4"
//android:minHeight="@dimen/min_height_for_text_view_case_1_4"
//android:background="@drawable/bg_text_view_case_1_4"
//android:gravity="center_vertical"
//android:paddingStart="@dimen/pading_start_for_text_view_case_1_4"
//android:text="@{viewModel.fourthCase}"
//android:textSize="@dimen/text_size_text_view_case_1_4"
//android:textColor="@color/task_text"
///>
//
//
//
//
//</LinearLayout>
//</androidx.cardview.widget.CardView>
//</LinearLayout>