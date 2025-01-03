package com.example.todolist_demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist_demo.data.TodoItem

class TodoItemViewModel internal constructor(private val todoListRepository: TodoListRepository): ViewModel() {
    var todoList: MutableLiveData<List<TodoItem>>? = null;
}


