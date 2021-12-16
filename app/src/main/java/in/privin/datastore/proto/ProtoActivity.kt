package `in`.privin.datastore.proto

import `in`.privin.datastore.R
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProtoActivity: AppCompatActivity() {

    lateinit var protoManager: ProtoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proto)
        protoManager = ProtoManager(this)
        renderCounter()
        findViewById<Button>(R.id.buttonCounter).setOnClickListener {
            updateCounter()
        }
        findViewById<Button>(R.id.buttonClearCounter).setOnClickListener {
            clearCounter()
        }
    }

    private fun updateCounter(){
        lifecycleScope.launch {
            protoManager.updateCounter()
        }
    }

    private fun clearCounter(){
        lifecycleScope.launch {
            protoManager.clearCounter()
        }
    }

    private fun renderCounter(){
        val textView = findViewById<TextView>(R.id.textViewCounter)
        lifecycleScope.launchWhenResumed {
            protoManager.counter.collect {
                textView.text = it.counter.toString()
            }
        }
    }
}