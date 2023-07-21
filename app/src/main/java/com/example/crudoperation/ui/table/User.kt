package com.example.crudoperation.ui.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "user_email") val userEmail: String,
    @ColumnInfo(name = "user_mobile") val userMobile: String,
)
