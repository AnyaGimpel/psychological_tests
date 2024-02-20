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

class MakingDecisionsTest : ComponentActivity() {

    var questionNo = -1
    val questions = Questions().MakingDecisions
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
    var C = 0
    var D = 0
    var E = 0
    var F = 0

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

                val intent = Intent(this@MakingDecisionsTest, WinActivity::class.java)
                val res1 = A + E
                val res2 = B + F
                val res3 = C + D

                val maxVariableName = when {
                    res1 > res2 && res1 > res3 -> "res1"
                    res2 > res1 && res2 > res3 -> "res2"
                    res3 > res1 && res3 > res2 -> "res3"
                    res1 == res2 && res1 > res3 -> "res4"
                    res2 == res3 && res2 > res1 -> "res5"
                    res1 == res3 && res1 > res2 -> "res6"
                    else -> "None"
                }

                val resultText = when (maxVariableName) {
                    "res1" -> "Вы не особенно решительный (в принятии решений) человек. Но Вас нельзя назвать и нерешительным. Вы действуете не всегда достаточно активно и быстро, но только потому, что считаете – дело того не стоит. Вам нравятся отважные люди. Но часто Вы оправдываете и нерешительных, считая, что их действия – результат не страха, а осмотрительности и осторожности."
                    "res2" -> "Вы, безусловно, решительный (в принятии решений) человек. Вы слишком часто пренебрегаете вещами, которые считаете мелкими, незначительными. Но, несмотря на это, Вас ценят как самостоятельную и интересную личность. Если у Вас есть еще и чувство ответственности, то Вам часто поручают сложные задания, но в этом случае в Вашей группе должны быть люди другого типа, которые бы уравновешивали Вашу слишком большую активность. "
                    "res3" -> "Вы боитесь не только принимать решения, но даже обдумывать их, страшась приближающихся событий. Ваше психологическое состояние нельзя назвать стабильным, благополучным. Часто Вы скорее ожидаете критики ваших действий, чем похвалы."
                    "res4" -> " Вы решительный человек в зависимости от ситуации. Вы слишком часто пренебрегаете вещами, которые считаете мелкими, незначительными. Вы действуете не всегда достаточно активно и быстро, но только потому, что считаете – дело того не стоит. Тем не менее Вам часто поручают сложные задания, потому что вы умеете брать ответственность в важных вещах и проявлять в этом деле большую активность."
                    "res5" -> "Вы, безусловно, решительный (в принятии решений) человек. Вы слишком часто пренебрегаете вещами, которые считаете мелкими, незначительными. Но, несмотря на это, Вас ценят как активную и прямолинейную личность. Но часто Вы ожидаете критики ваших действий, чем похвалы. Вы прете как танк,не думая в моменте о последствиях, но критика может поумерить ваш пыл. . Не нужно ли Вам все же лучше продумывать решения, которые Вы принимаете?"
                    "res6" -> "Вы не особенно решительный (в принятии решений) человек. Вы действуете не всегда достаточно активно и быстро. Часто Вы скорее ожидаете критики ваших действий, чем похвалы. Вам нравятся отважные люди. Но часто Вы оправдываете и нерешительных, считая, что их действия – результат не страха, а осмотрительности и осторожности."
                    else -> "No variable has the largest value"
                }

                intent.putExtra("res", resultText)
                startActivity(intent)
            }
        }
    }
}
