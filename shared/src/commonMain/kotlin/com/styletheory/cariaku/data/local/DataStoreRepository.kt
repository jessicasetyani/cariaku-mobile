package com.styletheory.cariaku.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class DataStoreRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USER_ID_KEY = stringPreferencesKey(name = "user_id")
        val SESSION_TOKEN_KEY = stringPreferencesKey(name = "session_token")
    }

    suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveUserId(userId: String): Boolean =
        try {
            dataStore.edit { preferences ->
                preferences.set(key = USER_ID_KEY, value = userId)
            }
            true
        } catch(e: Exception) {
            println("saveUserId() Error: $e")
            false
        }

    fun getUserId(): Flow<String> =
        dataStore.data
            .catch { emptyFlow<String>() }
            .map { preferences ->
                preferences[USER_ID_KEY] ?: ""
            }

    suspend fun saveSessionToken(sessionToken: String): Boolean =
        try {
            dataStore.edit { preferences ->
                preferences.set(key = SESSION_TOKEN_KEY, value = sessionToken)
            }
            true
        } catch(e: Exception) {
            println("saveSessionToken() Error: $e")
            false
        }

    fun getSessionToken(): Flow<String> =
        dataStore.data
            .catch { emptyFlow<String>() }
            .map { preferences ->
                preferences[SESSION_TOKEN_KEY] ?: ""
            }
}