package com.example.gourmet_inventory_mobile.utils

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.example.gourmet_inventory_mobile.model.Usuario.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val Context.dataStore by preferencesDataStore(name = "api_preferences")

class DataStoreUtils(private val context: Context) : ViewModel() {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("api_token")
        private val USER_DATA_KEY = stringPreferencesKey("user_data")
        private val ROLE_KEY = stringPreferencesKey("role")
    }

    suspend fun salvarToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    fun obterToken(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    suspend fun obterUsuario(): Flow<User>? {
        val preferences = context.dataStore.data.firstOrNull() ?: return null
        val jsonString = preferences[USER_DATA_KEY] ?: return null

        return try {
            flow {
                emit(Json.decodeFromString<User>(jsonString))
            }
        } catch (e: SerializationException) {
            null
        }
    }

    suspend fun salvarUsusario(user: User) {
        val jsonString = Json.encodeToString(user)
        context.dataStore.edit { prefs ->
            prefs[USER_DATA_KEY] = jsonString
        }
    }

    suspend fun limparDados() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(USER_DATA_KEY)
        }
    }

    suspend fun salvarCargo(role: String) {
        Log.d("DataStoreUtils", "Salvando cargo: $role")
        context.dataStore.edit { preferences ->
            preferences[ROLE_KEY] = role
        }
    }

    fun obterCargo(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[ROLE_KEY]
        }
    }
}