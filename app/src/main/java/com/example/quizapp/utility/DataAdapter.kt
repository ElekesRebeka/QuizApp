package com.example.quizapp.utility

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.QuestionItem

class DataAdapter (
    private val list: List<QuestionItem>,
    private val listener: OnItemClickListener
    ) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    interface OnItemClickListener{
        fun onDetailsClick(position: Int)
        fun onDeleteClick(position: Int)
    }

        //var counter_create: Int = 0
        //var counter_bind: Int = 0


        // 1. user defined ViewHolder type - Embedded class!
        inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
            val question: TextView = itemView.findViewById(R.id.question)
            val answerType: TextView = itemView.findViewById(R.id.answerType)
            val correctAnswer: TextView = itemView.findViewById(R.id.correctAnswer)

            init{
                itemView.setOnClickListener(this)
            }
            override fun onClick(p0: View?) {
                val currentPosition = this.adapterPosition
//            Log.d("xxx", "AdapterPosition: $currentPosition")
                listener.onDetailsClick(currentPosition)

            }
        }


        // 2. Called only a few times = number of items on screen + a few more ones
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            //++counter_create
//        Log.d("xxx", "onCreateViewHolder: $counter_create")
            return DataViewHolder(itemView)
        }


        // 3. Called many times, when we scroll the list
        override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
            val currentItem = list[position]
            holder.question.text = currentItem.question
            holder.answerType.text = currentItem.answersType
            holder.correctAnswer.text = currentItem.correctAnswer
            //++counter_bind
            //Log.d("xxx", "onBindViewHolder: $counter_bind")
        }


        // 4.
        override fun getItemCount() = list.size
}