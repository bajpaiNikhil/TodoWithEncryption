package com.example.todowithencryption.data.repository

import com.example.todowithencryption.data.db.TodoItemDao
import com.example.todowithencryption.data.db.entities.TodoItem
import javax.inject.Inject


class TodoItemRepository @Inject constructor(
    private val todoItemDao : TodoItemDao
) {
    suspend fun upsert(item:TodoItem) = todoItemDao.upsert(item)
    suspend fun delete(item: TodoItem) = todoItemDao.delete(item)
    fun getAllTodoItem() = todoItemDao.getAllTodoItem()
}