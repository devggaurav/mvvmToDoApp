package com.gc.gcmvvmtodo.ui.add_edit_todo

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String) : AddEditTodoEvent()
    data class OnDescriptionChange(val description: String) : AddEditTodoEvent()
    object OnSaveTodoClick : AddEditTodoEvent()
}
