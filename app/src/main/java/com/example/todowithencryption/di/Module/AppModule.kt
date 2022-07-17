package com.example.todowithencryption.di.Module

import android.content.Context
import androidx.room.Room
import com.example.todowithencryption.data.db.TodoItemDao
import com.example.todowithencryption.data.db.TodoItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app : Context):TodoItemDatabase{
        return Room.databaseBuilder(
            app ,
            TodoItemDatabase::class.java ,
            "TodoListDatabase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseDao(db:TodoItemDatabase): TodoItemDao{
        return db.getTodoItemDao()
    }
}