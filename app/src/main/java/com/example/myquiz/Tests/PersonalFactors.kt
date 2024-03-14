package com.example.myquiz.Tests

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.myquiz.Question
import com.example.myquiz.Questions
import com.example.myquiz.R
import com.example.myquiz.WinActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonalFactors : ComponentActivity() {

    var questionNo = -1
    val questions = Questions().PersonalFactors
    var sr = listOf<Question>()

    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonC: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var questionView: TextView
    private lateinit var testName: TextView

    var A = 0
    var B = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        buttonA = findViewById(R.id.button1)
        buttonB = findViewById(R.id.button2)
        buttonC = findViewById(R.id.button3)

        //деактивируем ненужные кнопки
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button4.visibility = View.INVISIBLE
        button4.isEnabled = false
        button5.visibility = View.INVISIBLE
        button5.isEnabled = false

        questionView = findViewById(R.id.questionView)
        testName = findViewById(R.id.testName)

        //Получаем id теста, определяем название теста, первый вопрос
        CoroutineScope(Dispatchers.Main).launch {
            val position = intent.getIntExtra("itemId",-1)
            val questions = Questions()
            var  thisTest = questions.dataTest[position]
            testName.text = thisTest.name
            updateQuestion()
        }

        buttonA.setOnClickListener {
            when{
                questionNo > 0 && questionNo < 12 -> A++
                questionNo > 0 && questionNo < 12 -> B++
            }
            CoroutineScope(Dispatchers.Main).launch {
                updateQuestion()
            }
        }

        buttonB.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                updateQuestion()
            }
        }

        buttonC.setOnClickListener {
            when{
                questionNo > 0 && questionNo < 12 -> A--
                questionNo > 0 && questionNo < 12 -> B--
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
                Question(
                    currentQuestion.question,
                    listOf(
                        currentQuestion.answers[0],
                        currentQuestion.answers[1],
                        currentQuestion.answers[2]
                    )
                )
            )
            withContext(Dispatchers.Main) {
                questionView.setText(currentQuestion.question)
                buttonA.setText(currentQuestion.answers[0])
                buttonB.setText(currentQuestion.answers[1])
                buttonC.setText(currentQuestion.answers[2])
            }
        } else {
            withContext(Dispatchers.Main) {

                val intent = Intent(this@PersonalFactors, WinActivity::class.java)

                val resultTextA = when {
                    A >= 7 && A <= 12 -> "Завышенный"
                    A >= -6 && A <= 6 -> "Типичный"
                    A >= -12 && A <= -7 -> "Заниженный"
                    B >= 7 && B <= 12 -> "Завышенный"
                    B >= -6 && B <= 6 -> "Типичный"
                    B >= -12 && B <= -7 -> "Заниженный"
                    else -> "No variable has the largest value"
                }

                val resultTextB = when {
                    A >= 7 && A <= 12 -> "Завышенный"
                    A >= -6 && A <= 6 -> "Типичный"
                    A >= -12 && A <= -7 -> "Заниженный"
                    B >= 7 && B <= 12 -> "Завышенный"
                    B >= -6 && B <= 6 -> "Типичный"
                    B >= -12 && B <= -7 -> "Заниженный"
                    else -> "No variable has the largest value"
                }

                intent.putExtra("res", "Рациональность - " + resultTextA + "\nГотовность к риску - " + resultTextB + "\nСубъективная рациональность: " +
                        "\n" +
                        " \nГотовность обдумывать свои решения и действовать при возможно полной ориентировке в ситуации, что может характеризовать разные, в том числе и рискованные решения субъекта.\n" +
                        "\n" +
                        "Личностная готовность к риску: " +
                        "Личностное свойство саморегуляции, позволяющее человеку принимать решения и действовать в ситуациях неопределенности. Как индивидуальная характеристика эта готовность предполагает также оценку субъектом своего прошлого опыта ")
                startActivity(intent)
            }
        }
    }
}
