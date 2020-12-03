package com.example.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.models.Income

class IncomeAdapter(private val incomeList: List<Income>):RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    class IncomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var incomeName: TextView = itemView.findViewById(R.id.income_name_layout_textView)
        var incomeValue: TextView = itemView.findViewById(R.id.income_value_layout_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_income_layout, parent, false)
        return IncomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val income = incomeList[position]

        holder.incomeName.text = income.name
        holder.incomeValue.text = income.value.toString()
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

}