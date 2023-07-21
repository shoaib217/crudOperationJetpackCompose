package com.example.crudoperation.ui

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crudoperation.ui.table.User
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(user: User)

    @Query("SELECT * FROM user")
    fun getAllSavedData(): Flow<List<User>>
}