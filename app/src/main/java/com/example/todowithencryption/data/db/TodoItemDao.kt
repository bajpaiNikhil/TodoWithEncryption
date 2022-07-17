package com.example.todowithencryption.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todowithencryption.data.db.entities.TodoItem


@Dao
interface TodoItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item:TodoItem)

    @Delete
    suspend fun delete(item:TodoItem)

    @Query("SELECT * FROM TodoListEncryption")
    fun getAllTodoItem(): LiveData<List<TodoItem>>

}