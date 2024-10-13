package com.styletheory.cariaku.android.util

object UserSession {
    private var isLoggedIn: Boolean = false

    fun isUserLoggedIn(): Boolean {
        return isLoggedIn
    }

    fun logIn() {
        isLoggedIn = true
    }

    fun logOut() {
        isLoggedIn = false
    }
}
