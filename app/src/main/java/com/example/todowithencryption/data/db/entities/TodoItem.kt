package com.example.todowithencryption.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TodoListEncryption")
data class TodoItem(
    @ColumnInfo(name = "TodoItemTitle")
    val todoItemTitle : String ,

    @ColumnInfo(name = "TodoItemDescription")
    val todoItemDescription : String ,

    @ColumnInfo(name = "TodoItemPriority")
    val todoItemPriority : String ,

    @ColumnInfo(name = "TodoItemCreation")
    val todoItemCreation : String
){
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId:Int? =null
}