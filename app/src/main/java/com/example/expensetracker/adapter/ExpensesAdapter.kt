package com.example.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.models.Expense

class ExpensesAdapter(private val expensesList: List<Expense>):RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>(){

    class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expenseName: TextView = itemView.findViewById(R.id.expense_layout_name_textView)
        var expenseValue: TextView = itemView.findViewById(R.id.expense_layout_value_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense_layout, parent, false)
        return ExpensesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        val expenses = expensesList[position]

        holder.expenseName.text = expenses.name
        holder.expenseValue.text = expenses.value.toString()
    }

    override fun getItemCount(): Int {
        return expensesList.size
    }
}