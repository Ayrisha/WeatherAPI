package com.example.weather.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.weather.domain.manager.SharedPrefManager
import com.example.weather.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SharedPrefManagerImpl @Inject constructor(
    private val application: Application
) : SharedPrefManager {
    override suspend fun saveCityData(id: Int?, name: String?) {
        application.dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                id?.let { this[PreferenceKeys.CITY_ID] = it }
                name?.let { this[PreferenceKeys.CITY_NAME] = it }
            }
        }
    }

    override fun readCityData(): Flow<Pair<Int?, String?>> {
        return application.dataStore.data.map { preferences ->
            Pair(
                preferences[PreferenceKeys.CITY_ID],
                preferences[PreferenceKeys.CITY_NAME]
            )
        }
    }

    override suspend fun removeCityData() {
        application.dataStore.edit { preferences ->
            preferences.remove(PreferenceKeys.CITY_ID)
            preferences.remove(PreferenceKeys.CITY_NAME)
        }
    }


}
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "id")

private object PreferenceKeys {
    val CITY_ID = intPreferencesKey(Constants.CITY_ID)
    val CITY_NAME = stringPreferencesKey(Constants.CITY_NAME)
}