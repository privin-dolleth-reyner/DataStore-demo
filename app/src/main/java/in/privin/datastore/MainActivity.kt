package `in`.privin.datastore

import `in`.privin.datastore.pref.PrefActivity
import `in`.privin.datastore.proto.ProtoActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_1).setOnClickListener {
            startActivity(Intent(this, PrefActivity::class.java))
        }
        findViewById<Button>(R.id.button_2).setOnClickListener {
            startActivity(Intent(this, ProtoActivity::class.java))
        }
    }
}