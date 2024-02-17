package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        val replayButton: Button = findViewById(R.id.replayButton)
        val exitButton: Button = findViewById(R.id.exitButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        val intent = intent
        val resultText = intent.getStringExtra("res")


        resultTextView.text = resultText

        replayButton.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}