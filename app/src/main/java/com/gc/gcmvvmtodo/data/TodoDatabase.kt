package com.gc.gcmvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {

    abstract val dao: TodoDao
}