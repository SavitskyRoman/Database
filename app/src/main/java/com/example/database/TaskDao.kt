package com.example.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)
    @Update
    suspend fun update(task: Task)
    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table WHERE task_unit = :unit")
    fun get(unit: String): LiveData<List<Task>>

    @Query("SELECT DISTINCT task_unit FROM task_table")
    fun getUnitName(): LiveData<List<String>>

    @Query("SELECT * FROM task_table WHERE taskId = :key")
    fun get(key: Long): LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>

}