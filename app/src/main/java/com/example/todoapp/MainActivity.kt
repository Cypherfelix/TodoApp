package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvToDoItem.adapter = todoAdapter
        rvToDoItem.layoutManager = LinearLayoutManager(this)


        btnAdd.setOnClickListener {
            val todoTittle = etToDoTittle.text.toString()

            if (todoTittle.isNotEmpty()){
                val todo = ToDo(todoTittle)
                todoAdapter.addTodo(todo)
                etToDoTittle.text.clear()
            }
        }

        btnDelete.setOnClickListener {
            todoAdapter.deleteTodo()
        }
    }
}