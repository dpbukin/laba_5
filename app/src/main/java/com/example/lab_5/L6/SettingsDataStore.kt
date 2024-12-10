package com.example.lab_5.L6

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore(name = "settings")

object SettingsDataStore {
    val EMAIL_KEY = stringPreferencesKey("email")

    suspend fun saveEmail(context: Context, email: String) {
        context.dataStore.edit { settings ->
            settings[EMAIL_KEY] = email
        }
    }

    fun getEmail(context: Context): Flow<String?> {
        return context.dataStore.data.map { settings ->
            settings[EMAIL_KEY]
        }
    }
}