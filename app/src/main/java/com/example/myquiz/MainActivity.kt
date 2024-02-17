package com.example.myquiz

import TestAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private lateinit var testList: List<Test>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.buttonStart)

        startButton.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        val questions = Questions()
        testList = questions.dataTest

        val testRecyclerView: RecyclerView = findViewById(R.id.recyclerview)
        testRecyclerView.layoutManager = LinearLayoutManager(this)
        val testAdapter = TestAdapter(testList)
        testRecyclerView.adapter = testAdapter
    }

}



