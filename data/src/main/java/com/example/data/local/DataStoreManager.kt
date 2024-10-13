package com.example.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.data.remote.dto.ProfileDataDto
import com.example.data.remote.dto.toDto
import com.example.domain.model.ProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class JwtTokenDataStore(private val dataStore: DataStore<Preferences>) :
    JwtTokenManager {

    companion object {
        val ACCESS_JWT_KEY = stringPreferencesKey("access_jwt")
        val REFRESH_JWT_KEY = stringPreferencesKey("refresh_jwt")
        val USER_DATA_KEY = stringPreferencesKey("user_data")
    }

    override suspend fun saveAccessJwt(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_JWT_KEY] = token
        }
    }

    override suspend fun saveRefreshJwt(token: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_JWT_KEY] = token
        }
    }

    override fun getAccessJwt(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[ACCESS_JWT_KEY]
        }
    }

    override suspend fun getRefreshJwt(): String? {
        return dataStore.data.map { preferences ->
            preferences[REFRESH_JWT_KEY]
        }.first()
    }

    override suspend fun clearAllTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_JWT_KEY)
            preferences.remove(REFRESH_JWT_KEY)
            preferences.remove(USER_DATA_KEY)
        }
    }

    override fun getUserData(): Flow<ProfileData?> {
        return dataStore.data.map { preferences ->
            preferences[USER_DATA_KEY]?.let {
                Json.decodeFromString<ProfileDataDto>(it).toModel()
            }
        }
    }

    override suspend fun saveUserData(data: ProfileData) {
        dataStore.edit { preferences ->
            preferences[USER_DATA_KEY] = Json.encodeToString(data.toDto())
        }
    }
}