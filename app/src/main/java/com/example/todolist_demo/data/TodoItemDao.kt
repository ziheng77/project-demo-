package com.example.todolist_demo.data

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoItemDao {
    @Insert
    fun insert(todoItem: TodoItem)
    @Query("DELETE FROM todo_table WHERE id = :id")
    fun delete(id:Int)
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTodos(): MutableLiveData<List<TodoItem>>
}