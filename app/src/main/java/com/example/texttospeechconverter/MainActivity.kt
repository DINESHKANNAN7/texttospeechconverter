package com.example.texttospeechconverter

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var tts: TextToSpeech
    private lateinit var txt:EditText
    private lateinit var btn: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txt=findViewById(R.id.input)
        btn=findViewById(R.id.btn)
        tts=TextToSpeech(applicationContext){status->
            if(status==TextToSpeech.SUCCESS){
                btn.setOnClickListener(){
                    tts.setLanguage(Locale.getDefault())
                    var tospeak:String=txt.text.toString()
                    tts.speak(tospeak,TextToSpeech.QUEUE_FLUSH,null,null)
                }

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }
}