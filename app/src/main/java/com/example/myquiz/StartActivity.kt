package com.example.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startButton: Button = findViewById(R.id.startButton)

            // принять значение с MainActivity, в зависимости от него отобразить название и описание
            // передать на активность теста

        val TestName: TextView = findViewById(R.id.TestName)
        val TestDesc: TextView = findViewById(R.id.TestDesc)

        val questions = Questions()
        val thisTest = questions.dataTest[0]
        TestName.text = thisTest.name
        TestDesc.text = thisTest.description

        startButton.setOnClickListener {
            val intent = Intent(this, MakingDecisionsTest::class.java)
            startActivity(intent)
        }
    }
}