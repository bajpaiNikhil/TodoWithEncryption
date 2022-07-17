package com.example.todowithencryption.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todowithencryption.data.db.entities.TodoItem


@Database(
    entities = [TodoItem::class] ,
    version = 1
)
abstract class TodoItemDatabase :RoomDatabase(){
    abstract fun getTodoItemDao() : TodoItemDao
}