package com.gc.gcmvvmtodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

@Entity
data class Todo(
    val title: String,
    val description: String,
    val isDone: Boolean,
   @PrimaryKey val id: Int? = null


)
