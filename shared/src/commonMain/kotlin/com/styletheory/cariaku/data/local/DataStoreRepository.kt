package com.styletheory.cariaku.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.styletheory.cariaku.data.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DataStoreRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        val USER_ID_KEY = stringPreferencesKey(name = "user_id")
        val SESSION_TOKEN_KEY = stringPreferencesKey(name = "session_token")
        val USER_PROFILE_KEY = stringPreferencesKey(name = "user_profile")
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

    suspend fun saveUserProfile(userProfile: UserProfile): Boolean =
        try {
            val userProfileJson = Json.encodeToString(userProfile)
            dataStore.edit { preferences ->
                preferences.set(key = USER_PROFILE_KEY, value = userProfileJson)
            }
            true
        } catch (e: Exception) {
            println("saveUserProfile() Error: $e")
            false
        }

    fun getUserProfile(): Flow<UserProfile?> =
        dataStore.data
            .catch { emptyFlow<Preferences>() }
            .map { preferences ->
                preferences[USER_PROFILE_KEY]?.let {
                    Json.decodeFromString<UserProfile>(it)
                }
            }
}