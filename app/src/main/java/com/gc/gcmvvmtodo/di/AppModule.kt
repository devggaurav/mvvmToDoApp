package com.gc.gcmvvmtodo.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gc.gcmvvmtodo.data.TodoDatabase
import com.gc.gcmvvmtodo.data.TodoRepository
import com.gc.gcmvvmtodo.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//
// Created by gaurav on 14/01/22.
// Copyright (c) 2022 gc. All rights reserved.
//

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(app, TodoDatabase::class.java, "todo_db").build()

    }


    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.dao)
    }

}