package com.example.myapplica

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myquiz.R
import com.example.myquiz.StartActivity
import com.example.myquiz.Test

class TestAdapter(private val testList: List<Test>) : RecyclerView.Adapter<TestAdapter.TestViewHolder>(), Filterable {

    private var filteredTestList: List<Test> = testList
    private var originalTestList: List<Test> = testList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        return TestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val currentTest = originalTestList.indexOf(filteredTestList[position]) // Получаем позицию элемента в исходном списке
        holder.testName.text = filteredTestList[position].name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StartActivity::class.java)
            intent.putExtra("itemId", currentTest) // Используем позицию из исходного списка
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filteredTestList.size
    }

    inner class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val testName: TextView = itemView.findViewById(R.id.testName)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                filteredTestList = if (charString.isEmpty()) {
                    originalTestList // Используем исходный список, если строка фильтра пустая
                } else {
                    val filteredList = originalTestList.filter { it.name.toLowerCase().contains(charString.toLowerCase()) }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredTestList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredTestList = filterResults.values as List<Test>
                notifyDataSetChanged()
            }
        }
    }
}