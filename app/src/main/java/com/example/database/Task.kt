package com.example.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
@kotlinx.serialization.Serializable
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,
    @ColumnInfo(name = "task_unit")
    var taskUnit: String = "",
    @ColumnInfo(name = "task_name")
    var taskName: String = "",
    @ColumnInfo(name = "task_transcription")
    var taskTranscription: String = "",
    @ColumnInfo(name = "task_done")
    var taskDone: Boolean = false,
    @ColumnInfo(name = "task_translation")
    var taskTranslation: String = ""
)