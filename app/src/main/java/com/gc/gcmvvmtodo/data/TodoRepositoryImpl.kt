package com.gc.gcmvvmtodo.data

import kotlinx.coroutines.flow.Flow

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

class TodoRepositoryImpl(private val dao: TodoDao) : TodoRepository {


    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTod(todo: Todo) {
        dao.deleteTod(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}