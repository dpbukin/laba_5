package com.example.lab_5.model


object UserSession {
    private val userList = mutableListOf<User>()

    fun getUsers(): List<User> = userList

    fun addUser(user: User) {
        userList.add(user)
    }

    fun authenticateUser(login: String, pass: String): User? {
        return userList.find { it.login == login && it.pass == pass }
    }

}
