package com.example.myapplica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.myquiz.Tests.MakingDecisionsTest

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startButton: Button = findViewById(R.id.startButton)
        val TestName: TextView = findViewById(R.id.TestName)
        val TestDesc: TextView = findViewById(R.id.TestDesc)
        val closeImg: ImageView = findViewById(R.id.imageView)

        val intent = intent
        val position = intent.getIntExtra("itemId",-1)

        val questions = Questions()
        var  thisTest = questions.dataTest[position]
        TestName.text = thisTest.name
        TestDesc.text = thisTest.description

        startButton.setOnClickListener {
            val intent = when (position) {
                0 ->  Intent(this, MakingDecisionsTest::class.java)
                1 -> Intent(this, MakingDecisionsTest::class.java)
                2 -> Intent(this, MakingDecisionsTest::class.java)

                else -> {}
            }
            if (intent is Intent) {
                intent.putExtra("itemId", position)
                startActivity(intent)
            }

        }

        closeImg.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}