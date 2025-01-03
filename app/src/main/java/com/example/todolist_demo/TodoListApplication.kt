package com.example.todolist_demo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.todolist_demo.data.TodoDatabase
import com.example.todolist_demo.data.TodoItemDao

class TodoListApplication : Application(){
    // 通过database单例模式创建Dao对象
    val todoItemDao: TodoItemDao by lazy { TodoDatabase.getInstance(this).TodoItemDao() }

    override fun onCreate() {
        super.onCreate()
        context = this // 为Context提供全局访问
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}