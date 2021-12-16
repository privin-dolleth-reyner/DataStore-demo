package `in`.privin.datastore.proto

import `in`.privin.datastore.Settings
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoManager constructor(private val context: Context) {

    val counter = context.settingsDataStore.data.catch { exception ->
        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            Log.e("ProtoManager", "Error reading sort order preferences.", exception)
            emit(Settings.getDefaultInstance())
        } else {
            throw exception
        }
    }
    suspend fun updateCounter(){
        context.settingsDataStore.updateData { settings ->
            val counter = settings.counter + 1
            settings.toBuilder().setCounter(counter).build()
        }
    }

    suspend fun clearCounter(){
        context.settingsDataStore.updateData { settings ->
            settings.toBuilder().clearCounter().build()
        }
    }

    companion object {
        val Context.settingsDataStore: DataStore<Settings> by dataStore(
            fileName = "settings.pb",
            serializer = SettingsSerializer
        )
    }
}