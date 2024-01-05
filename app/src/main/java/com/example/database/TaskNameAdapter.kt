package com.example.database

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.TaskItemAdapter.TaskItemViewHolder.Companion.inflateFrom
import com.example.database.generated.callback.OnClickListener

class TaskNameAdapter(val clickListener: (taskUnit: String) -> Unit) :
    RecyclerView.Adapter<TaskNameAdapter.TaskNameViewHolder>() {
    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TaskNameViewHolder = TaskNameViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskNameViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,clickListener)
    }

    class TaskNameViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        val taskName = rootView.findViewById<TextView>(R.id.task_name_name)

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskNameViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.task_name, parent, false) as CardView
                return TaskNameViewHolder(view)
            }
        }

        fun bind(item: String, clickListener: (taskUnit: String) -> Unit) {
            taskName.text = item
            rootView.setOnClickListener { clickListener(item) }
        }
    }
}