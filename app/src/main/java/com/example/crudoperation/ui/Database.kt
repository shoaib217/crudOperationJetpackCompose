package com.example.crudoperation.ui

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crudoperation.ui.table.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {

    abstract fun getDao() : Dao

    companion object{
        private var instance:MyDatabase?=null


        @Synchronized
        fun getDatabase(context: Context):MyDatabase{
            if (instance ==null){
                instance = Room.databaseBuilder(context,MyDatabase::class.java,"crud_operation.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}