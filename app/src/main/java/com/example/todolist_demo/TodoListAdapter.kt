package com.example.todolist_demo

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class TodoListAdapter(private val todoList: MutableList<TodoItem>):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    // 创建视图项
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false);
        return TodoViewHolder(view)
    }

    // 在每次更新ViewHolder的时候调用
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position];
        holder.tvTodo.text = todo.text;
        holder.checkBox.isChecked = todo.isCompleted;
        println("----------ps:"+position)
        // 清除之前的监听器
        holder.checkBox.setOnCheckedChangeListener(null)
        holder.btnDeleteTodo.setOnClickListener(null)
        holder.itemView.setOnLongClickListener(null)
        // 判断任务完成
        if(todo.isCompleted){
            holder.tvTodo.paintFlags = holder.tvTodo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG;
            holder.tvTodo.setTextColor(holder.tvTodo.context.getColor(android.R.color.darker_gray));
        }else{
            holder.tvTodo.paintFlags = holder.tvTodo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv();
            holder.tvTodo.setTextColor(holder.tvTodo.context.getColor(android.R.color.black));
        }
        // 监听勾选完成
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            // 这里为了解决recyclerview复用问题导致的position获取不准确，需要使用重新获取position
            todoList.get(holder.bindingAdapterPosition).isCompleted = isChecked
            holder.itemView.post{
                notifyItemChanged(holder.bindingAdapterPosition,todo)
            }
        }
        // 按钮删除Todo
        holder.btnDeleteTodo.setOnClickListener{
            holder.btnDeleteTodo.isEnabled = false
            val recentlyDeletedTodo = todo
            val recentlyDeletedPosition = position
            println("delete:"+position)
            todoList.removeAt(position)
            notifyItemRemoved(position) // 通知RecyclerView更新对应项视图
            notifyItemRangeChanged(position, todoList.count()) // 刷新position，防止越界
            println("now todoList:"+todoList)
            Snackbar.make(it,"撤销删除",Snackbar.LENGTH_LONG).setAction("立即撤销") {
                todoList.add(recentlyDeletedPosition,recentlyDeletedTodo)
                notifyItemInserted(recentlyDeletedPosition)
                println("chexiaohou:"+todoList)
            }.show()
            holder.btnDeleteTodo.postDelayed({ holder.btnDeleteTodo.isEnabled = true }, 500)
        }
        // 长按删除Todo
        holder.itemView.setOnLongClickListener {
            val recentlyDeletedTodo = todo
            val recentlyDeletedPosition = position
            println("delete:"+position)
            todoList.removeAt(position)
            notifyItemRemoved(position) // 通知RecyclerView更新对应项视图
            println("now todoList:"+todoList)
            Snackbar.make(it,"撤销删除",Snackbar.LENGTH_LONG).setAction("立即撤销") {
                todoList.add(recentlyDeletedPosition,recentlyDeletedTodo)
                notifyItemInserted(recentlyDeletedPosition)
                println("chexiaohou:"+todoList)
            }.show()
            true
        }
    }

    override fun getItemCount(): Int = todoList.size

    fun addTodo(todoItem: TodoItem){
        todoList.add(todoItem)
        println("Todo List: $todoList")
        notifyItemInserted(todoList.size - 1)
    }


    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTodo: TextView = itemView.findViewById(R.id.tvTodo);
        val checkBox: CheckBox = itemView.findViewById(R.id.cbTodo);
        val btnDeleteTodo: Button = itemView.findViewById(R.id.btnDeleteTodo);
    }

}