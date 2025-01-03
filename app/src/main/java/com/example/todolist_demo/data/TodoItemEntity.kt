package com.example.todolist_demo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class TodoItem(
//    var text:String,
//    var isCompleted: Boolean = false
//)

@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey
    val id: Long = 0, // 自动生成主键
    @ColumnInfo("todo_text")
    var text: String,
    @ColumnInfo("todo_iscompleted")
    var isCompleted: Boolean = false
)