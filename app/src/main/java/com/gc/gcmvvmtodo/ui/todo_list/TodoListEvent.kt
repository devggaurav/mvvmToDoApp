package com.gc.gcmvvmtodo.ui.todo_list

import com.gc.gcmvvmtodo.data.Todo

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

sealed class TodoListEvent {


    data class OnDeleteTodoClick(val todo: Todo) : TodoListEvent()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean) : TodoListEvent()
    object OnUndoDeleteClick : TodoListEvent()
    data class OnTodoClick(val todo: Todo) : TodoListEvent()
    object OnAddTodoClick : TodoListEvent()


}
