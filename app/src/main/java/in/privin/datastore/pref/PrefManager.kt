package `in`.privin.datastore.pref

import `in`.privin.datastore.UserPreferences
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefManager(private val context: Context) {

    val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val color = preferences[BG_COLOR] ?: android.R.color.white
            UserPreferences(color)
        }

    suspend fun updateColor(color: Int) {
        context.dataStore.edit { preferences ->
            preferences[BG_COLOR] = color
        }
    }

    companion object {
        val BG_COLOR = intPreferencesKey("bg_color")
        private const val PREF = "settings"

        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREF)
    }
}