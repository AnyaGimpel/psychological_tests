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

        val toMainButton: Button = findViewById(R.id.toMainButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        val intent = intent
        val resultText = intent.getStringExtra("res")


        resultTextView.text = resultText

        toMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}