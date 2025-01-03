package com.example.todolist_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory

// 单例工厂模式，便于管理多个viewmodel
class ViewModelFactory private constructor(private val todoListRepository: TodoListRepository/*,other viewmodel*/):ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(TodoItemViewModel::class.java) -> {
                    TodoItemViewModel(todoListRepository)
                }
                // other viewmodel

                else -> throw IllegalArgumentException("Unknow ViewModel: ${modelClass.name}")
            }
        } as T
    }
    // 这里定义一个单例实例，确保 ViewModelFactory 只会有一个实例
    companion object {
        private var instance: ViewModelFactory? = null
        // getInstance方法可以接受多个参数，传入不同的Repository来创建对应的ViewModel
        fun getInstance(
            todoListRepository: TodoListRepository/*,other viewmodel*/
        ): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(todoListRepository/*,other viewmodel*/).also { instance = it }
            }
        }
    }
}