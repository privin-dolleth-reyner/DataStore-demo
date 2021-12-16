package `in`.privin.datastore.pref

import `in`.privin.datastore.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

class PrefActivity : AppCompatActivity() {

    private lateinit var outerView: View

    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pref)
        outerView = findViewById<View>(R.id.root)
        prefManager = PrefManager(this)
        readPreferences()

        findViewById<Button>(R.id.button_1).setOnClickListener {
            updatePreference(android.R.color.white)
        }
        findViewById<Button>(R.id.button_2).setOnClickListener {
            updatePreference(android.R.color.black)
        }
    }

    private fun updatePreference(color: Int) {
        lifecycleScope.launchWhenResumed {
            prefManager.updateColor(color)
        }
    }

    private fun readPreferences() {
        lifecycleScope.launchWhenResumed {
            prefManager.userPreferencesFlow.collect {
                outerView.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        it.color
                    )
                )
            }
        }
    }
}