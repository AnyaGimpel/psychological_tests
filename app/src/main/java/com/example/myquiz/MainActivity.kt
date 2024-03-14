package com.example.myquiz

import TestAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {

    private lateinit var testList: List<Test>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val questions = Questions()
        testList = questions.dataTest

        val testRecyclerView: RecyclerView = findViewById(R.id.recyclerview)
        testRecyclerView.layoutManager = LinearLayoutManager(this)
        val testAdapter = TestAdapter(testList)
        testRecyclerView.adapter = testAdapter

        val searchEditText: EditText = findViewById(R.id.searchTest)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
                // Вызов метода фильтрации данных в адаптере при изменении текста в поисковой строке
                testAdapter.filter.filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

    }

}




