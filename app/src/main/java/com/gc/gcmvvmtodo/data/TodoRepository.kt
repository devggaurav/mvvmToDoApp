package com.gc.gcmvvmtodo.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

interface TodoRepository
{

    suspend fun insertTodo(todo: Todo)


    suspend fun deleteTod(todo: Todo)


    suspend fun getTodoById(id : Int) : Todo?


    fun getTodos() : Flow<List<Todo>>

}