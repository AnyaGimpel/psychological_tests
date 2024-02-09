package com.example.myquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    var questionNo = 0
    val questions = Questions().MakingDecisions
    var sr = listOf<Question>()

    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonC: Button
    private lateinit var textView: TextView

    var A = 0
    var B = 0
    var C = 0
    var D = 0
    var E = 0
    var F = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonA = findViewById(R.id.button)
        buttonB = findViewById(R.id.button2)
        buttonC = findViewById(R.id.button3)
        textView = findViewById(R.id.textView)

        buttonA.setOnClickListener {
            if (questionNo % 2 == 0) {
                A++
            }else{
                D++
            }
            CoroutineScope(Dispatchers.Main).launch {
                updateQuestion()
            }
        }

        buttonB.setOnClickListener {
            if (questionNo % 2 == 0) {
                B++
            }else{
                E++
            }
            CoroutineScope(Dispatchers.Main).launch {
                updateQuestion()
            }
        }

        buttonC.setOnClickListener {
            if (questionNo % 2 == 0) {
                C++
            }else{
                F++
            }
            CoroutineScope(Dispatchers.Main).launch {
                updateQuestion()
            }
        }

    }


    suspend fun updateQuestion() {
        questionNo++
        if (questionNo < questions.size) {
            sr = listOf<Question>()
            val currentQuestion = questions.get(questionNo)
            sr = listOf(
                Question(currentQuestion.question, listOf(currentQuestion.answers[0], currentQuestion.answers[1], currentQuestion.answers[2]))
            )
            withContext(Dispatchers.Main) {
                textView.setText("${currentQuestion.question} \n\n A) ${currentQuestion.answers[0]} \n\n B) ${currentQuestion.answers[1]} \n" +
                        "\n" +
                        " C) ${currentQuestion.answers[2]}")
            }
        } else {
            withContext(Dispatchers.Main) {

                val intent = Intent(this@MainActivity, WinActivity::class.java)
                val res1 = A + E
                val res2 = B + F
                val res3 = C + D
                Log.d("MyTag", "А = $A")
                Log.d("MyTag", "B = $B")
                Log.d("MyTag", "C = $C")
                Log.d("MyTag", "D = $D")
                Log.d("MyTag", "E = $E")
                Log.d("MyTag", "F = $F")
                val maxVariableName = when {
                    res1 > res2 && res1 > res3 -> "res1"
                    res2 > res1 && res2 > res3 -> "res2"
                    res3 > res1 && res3 > res2 -> "res3"
                    res1 == res2 && res1 > res3 -> "res4"
                    res2 == res3 && res2 > res1 -> "res5"
                    res1 == res3 && res1 > res2 -> "res6"
                    else -> "None"
                }
                intent.putExtra("res", maxVariableName)
                startActivity(intent)
            }
        }
    }
}

data class Question(val question: String, val answers: List<String>) // Класс для представления вопроса