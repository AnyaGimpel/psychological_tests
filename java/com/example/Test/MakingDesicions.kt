package com.example.Test

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

class MakingDecisionsTest : ComponentActivity() {

    var questionNo = -1
    val questions = Questions().MakingDecisions
    var sr = listOf<Question>()

    private lateinit var buttonYes: Button
    private lateinit var buttonNo: Button
    private lateinit var questionView: TextView
    private lateinit var testName: TextView

    var Yes = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        buttonYes = findViewById(R.id.button1)
        buttonNo = findViewById(R.id.button2)

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

        buttonYes.setOnClickListener {
            Yes++
            CoroutineScope(Dispatchers.Main).launch {
                updateQuestion()
            }
        }

        buttonB.setOnClickListener {
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
                buttonYes.setText(currentQuestion.answers[0])
                buttonNo.setText(currentQuestion.answers[1])
            }
        } else {
            withContext(Dispatchers.Main) {

                val intent = Intent(this@MakingDecisionsTest, WinActivity::class.java)

                val maxVariableName = when {
                    Yes <= 5 -> "res1"
                    Yes > 6 && Yes < 10 -> "res2"
                    Yes >= 10  -> "res3"
                }

                val resultText = when (maxVariableName) {
                    "res1" -> "Ты умеешь делегировать."
                    "res2" -> "Следует научиться делегировать и первый шаг «Найм ассистента»."
                    "res3" -> "У тебя проблемы с делегированием: научись доверять людям и пойми, что рост возможен только в команде."
                }

                intent.putExtra("res", resultText)
                startActivity(intent)
            }
        }
    }
}