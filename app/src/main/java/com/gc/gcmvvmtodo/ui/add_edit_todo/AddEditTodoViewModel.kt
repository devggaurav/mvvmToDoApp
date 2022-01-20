package com.gc.gcmvvmtodo.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gc.gcmvvmtodo.data.Todo
import com.gc.gcmvvmtodo.data.TodoRepository
import com.gc.gcmvvmtodo.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var todo by mutableStateOf<Todo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set


    private val _uiEvent = Channel<UIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    init {

        val todoId = savedStateHandle.get<Int>("todoId")
        if (todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId!!)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: " "
                    this@AddEditTodoViewModel.todo = todo
                }

            }
        }
    }

    fun OnEvent(event: AddEditTodoEvent) {
        when (event) {
            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description

            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvents(UIEvent.ShowSnackBar(message = "Title can't be empty"))
                        return@launch
                    }
                    repository.insertTodo(
                        Todo(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    sendUiEvents(UIEvent.PopBackStack)
                }

            }


        }
    }

    private fun sendUiEvents(event: UIEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}