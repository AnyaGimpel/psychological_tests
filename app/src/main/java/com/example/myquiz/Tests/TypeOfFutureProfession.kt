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

class TypeOfFutureProfession : ComponentActivity() {

    var questionNo = -1
    val questions = Questions().TypeOfFutureProfession
    var sr = listOf<Question>()

    private lateinit var buttonA: Button
    private lateinit var buttonB: Button
    private lateinit var buttonC: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var questionView: TextView
    private lateinit var testName: TextView

    var P = 0
    var T = 0
    var Z = 0
    var E = 0
    var H = 0



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
                questionNo >= 0 && questionNo <= 4 -> P++
                questionNo >= 5 && questionNo <= 9 -> T++
                questionNo >= 10  && questionNo <= 14 -> Z++
                questionNo >= 15 && questionNo <= 19 -> E++
                questionNo >= 20 && questionNo <= 25 -> H++
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
                questionNo >= 0 && questionNo <= 4 -> P--
                questionNo >= 5 && questionNo <= 9 -> T--
                questionNo >= 10  && questionNo <= 14 -> Z--
                questionNo >= 15 && questionNo <= 19 -> E--
                questionNo >= 20 && questionNo <= 25 -> H--
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

                val intent = Intent(this@TypeOfFutureProfession, WinActivity::class.java)

                val resultTextP = when {
                    P >= 4 && P <= 5 -> "выраженный интерес"
                    P >= 2 && P <= 3 -> "умеренный интерес"
                    P >= 0 && P <= 1 -> "отсутствие интереса"
                    else -> "No variable has the largest value"
                }

                val resultTextT = when {
                    T >= 4 && T <= 5 -> "выраженный интерес"
                    T >= 2 && T <= 3 -> "умеренный интерес"
                    T >= 0 && T <= 1 -> "отсутствие интереса"
                    else -> "No variable has the largest value"
                }

                val resultTextZ = when {
                    Z >= 4 && Z <= 5 -> "выраженный интерес"
                    Z >= 2 && Z <= 3 -> "умеренный интерес"
                    Z >= 0 && Z <= 1 -> "отсутствие интереса"
                    else -> "No variable has the largest value"
                }

                val resultTextE = when {
                    E >= 4 && E <= 5 -> "выраженный интерес"
                    E >= 2 && E <= 3 -> "умеренный интерес"
                    E >= 0 && E <= 1 -> "отсутствие интереса"
                    else -> "No variable has the largest value"
                }

                val resultTextH = when {
                    H >= 4 && H <= 5 -> "выраженный интерес"
                    H >= 2 && H <= 3 -> "умеренный интерес"
                    H >= 0 && H <= 1 -> "отсутствие интереса"
                    else -> "No variable has the largest value"
                }

                intent.putExtra("res", "Природа - " + resultTextP + "\nТехника - " + resultTextT + "\nЗнак - " + resultTextZ +"\nИскусство - " + resultTextE +
                        "\nЧеловек - " + resultTextH + "\nНаибольшая сумма указывает на наиболее подходящий вам тип профессии. Методика, которую вы только что выполнили, основана на ваших профессиональных интересах. Все значительные профессиональные достижения выросли из интересов, которые при благоприятных условиях развились в склонности. ")
                startActivity(intent)
            }
        }
    }
}
