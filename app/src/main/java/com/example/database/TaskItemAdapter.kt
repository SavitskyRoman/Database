package com.example.database

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.TaskNameAdapter.TaskNameViewHolder.Companion.inflateFrom

class TaskItemAdapter (val clickListener: (taskId: Long) -> Unit): RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : TaskItemViewHolder = TaskItemViewHolder.inflateFrom(parent)
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener)
    }
    class TaskItemViewHolder(val rootView: CardView)
        : RecyclerView.ViewHolder(rootView) {
        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskTranscrioption = rootView.findViewById<TextView>(R.id.task_transcription)
        val taskTranslation = rootView.findViewById<TextView>(R.id.task_translation)
        val taskUnit = rootView.findViewById<TextView>(R.id.task_unit)
        val taskId = rootView.findViewById<TextView>(R.id.task_id)
        val taskDone = rootView.findViewById<TextView>(R.id.task_isTask)
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }
        fun bind(item: Task,clickListener: (taskId: Long) -> Unit ) {
            taskId.text = item.taskId.toString()
            taskName.text = item.taskName
            taskTranscrioption.text = item.taskTranscription
            taskTranslation.text = item.taskTranslation
            taskUnit.text = item.taskUnit
            taskDone.text = item.taskDone.toString()
            rootView.setOnClickListener { clickListener(item.taskId) }
        }
    }
}