package com.example.quizapp.utility

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.quizapp.models.QuestionItem
import com.example.quizapp.models.questions

class MainViewModel: ViewModel(){
    var list: MutableList<QuestionItem> = generateDummyList(questions.size).toMutableList()
    var currentPosition: Int = 0

    init {

    }

    fun addItem(item: QuestionItem) {
        list.add(item)
    }

    fun getItem(): QuestionItem {
        return list[currentPosition]
    }

    fun generateDummyList(size: Int): List<QuestionItem>{
        Log.i("xxx",size.toString()+ questions.size.toString())
        val list = ArrayList<QuestionItem>()
        for (i in 0 until size){
            val question = questions[i].text
            val answerType = "single answer"
            val correctAnswer = questions[i].answers[0]
            val item = QuestionItem(question,answerType,correctAnswer)
            list += item
        }
        return list
    }
}