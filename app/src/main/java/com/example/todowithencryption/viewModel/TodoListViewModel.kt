package com.example.todowithencryption.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todowithencryption.data.db.entities.TodoItem
import com.example.todowithencryption.data.repository.TodoItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoItemRepository
):ViewModel() {

    fun todoItemUpsert(item:TodoItem){
        viewModelScope.launch {
            repository.upsert(item)
        }
    }
    fun todoItemDelete(item: TodoItem){
        viewModelScope.launch {
            repository.delete(item)
        }
    }
    fun generateTodoList() = repository.getAllTodoItem()
}