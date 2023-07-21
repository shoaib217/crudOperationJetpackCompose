package com.example.crudoperation.ui

import com.example.crudoperation.ui.table.User

class Repository(private val dao: Dao) {


    fun insertUser(user: User) = dao.insertUserData(user)

    fun getUser() =  dao.getAllSavedData()
}