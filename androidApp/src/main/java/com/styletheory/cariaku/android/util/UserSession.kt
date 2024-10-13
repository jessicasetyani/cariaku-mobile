package com.styletheory.cariaku.android.util

import com.parse.ParseObject
import com.parse.ParseQuery

/**
 * Created by Jessica Setyani on 13-10-2024.
 */
class UserSession {
    fun isUserLoggedIn(): Boolean {
        return false
    }

    fun getUser(username: String) {
        //Filters objects in which a specific key’s value is equal to the provided value.
        val getUserQuery = ParseQuery.getQuery<ParseObject>(AssistantTable.NAME)
        getUserQuery.whereEqualTo(AssistantTable.ASSISTANT_NAME, username)
        getUserQuery.findInBackground { users, messageError ->

        }
    }
}