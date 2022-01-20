package com.gc.gcmvvmtodo.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gc.gcmvvmtodo.data.Todo
import com.gc.gcmvvmtodo.data.TodoRepository
import com.gc.gcmvvmtodo.util.Routes
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
class TodoListViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {


    val todos = repository.getTodos()

    private val _uiEvent = Channel<UIEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null


    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.OnTodoClick -> {
                sendUiEvents(UIEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))

            }
            is TodoListEvent.OnAddTodoClick -> {

                sendUiEvents(UIEvent.Navigate(Routes.ADD_EDIT_TODO))


            }
            is TodoListEvent.OnUndoDeleteClick -> {

                 deletedTodo?.let {
                     viewModelScope.launch {
                         repository.insertTodo(it)
                     }
                 }

            }
            is TodoListEvent.OnDeleteTodoClick -> {

                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTod(event.todo)
                    sendUiEvents(
                        UIEvent.ShowSnackBar(
                            message = "Todo deleted",
                            action = "undo"
                        )
                    )
                }

            }
            is TodoListEvent.OnDoneChange -> {

                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
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