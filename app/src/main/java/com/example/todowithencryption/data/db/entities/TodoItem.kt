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
    val todoItemCreation : String ,

    @ColumnInfo(name = "Salt")
    val salt : String ,

    @ColumnInfo(name = "Initialisation Vector")
    val iv : String ,

    @ColumnInfo(name = "Secret Key")
    val secretKey : String

){
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId:Int? =null
}