package com.gc.gcmvvmtodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

@Dao
interface TodoDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTod(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id : Int) : Todo?

    @Query("SELECT * FROM todo")
    fun getTodos() : Flow<List<Todo>>

}