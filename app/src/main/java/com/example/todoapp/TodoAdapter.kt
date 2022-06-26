package com.example.todoapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemtodo.view.*


class TodoAdapter(
    private val todos: MutableList<ToDo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemtodo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: ToDo){
        todos.add(todo);
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodo(){
        todos.removeAll{todo: ToDo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private  fun  toggleStrikeThrough(tvToDoTittle: TextView, isChecked: Boolean){
        if (isChecked){
            tvToDoTittle.paintFlags = tvToDoTittle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvToDoTittle.paintFlags = tvToDoTittle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val cur = todos[position]
        holder.itemView.apply {
            tvItemView.text = cur.title
            cbDone.isChecked = cur.isChecked
            toggleStrikeThrough(tvItemView,cur.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvItemView,isChecked)
                cur.isChecked = !cur.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}