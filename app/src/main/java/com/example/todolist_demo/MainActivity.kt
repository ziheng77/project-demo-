package com.example.todolist_demo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoListAdapter: TodoListAdapter;
    private lateinit var etTodo:EditText;
    private lateinit var btnAddTodo:Button;
    private lateinit var recyclerView: RecyclerView;
    private val todoList = mutableListOf<TodoItem>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        // 注入主页面
        setContentView(R.layout.activity_main);
        // 获取视图实例
        etTodo = findViewById(R.id.etTodo);
        btnAddTodo = findViewById(R.id.btnAddTodo);
        recyclerView = findViewById(R.id.recyclerView);
        todoListAdapter = TodoListAdapter(todoList);
        // 指定recycleView竖向排列
        recyclerView.layoutManager = LinearLayoutManager(this);
        // 设置recycleView的适配器
        recyclerView.adapter = todoListAdapter;
        // 监听添加todo
        btnAddTodo.setOnClickListener {
            val todoText = etTodo.text.toString()
            if (todoText.isNotEmpty()) {
                val todo = TodoItem(todoText)
                todoListAdapter.addTodo(todo)
                println(todoListAdapter.getItemCount())
                println("RecyclerView Visibility: ${recyclerView.visibility}")
                todoListAdapter.notifyDataSetChanged()
                etTodo.text.clear()

            }
        }
    }
}