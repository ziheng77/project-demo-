package com.example.todolist_demo

import com.example.todolist_demo.data.TodoItemDao

class TodoListRepository private constructor(private val todoItemDao: TodoItemDao) {



    companion object {
        @Volatile
        private var instance: TodoListRepository? = null
        fun getInstance(todoItemDao: TodoItemDao) = instance ?: synchronized(this) {
            instance ?: TodoListRepository(todoItemDao).also { instance = it }
        }
    }
}