package com.example.quizapp.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.TAG
import com.example.quizapp.models.Question
import com.example.quizapp.models.questions
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [quizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nextButton: Button
    private lateinit var questionText: TextView
    private lateinit var answer1: RadioButton
    private lateinit var answer2: RadioButton
    private lateinit var answer3: RadioButton
    private lateinit var answer4: RadioButton


    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    //private var questionIndex = 1
    private val numQuestions = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        view?.apply {
            initializeView(this)
            registerListeners(this)
            randomizeQuestions()
        }

        return view
    }

    private fun initializeView(view: View) {
        nextButton = view.findViewById(R.id.nextButton)
        questionText = view.findViewById(R.id.question)
        answer1 = view.findViewById(R.id.answer1)
        answer2 = view.findViewById(R.id.answer2)
        answer3 = view.findViewById(R.id.answer3)
        answer4 = view.findViewById(R.id.answer4)
        if (questionIndex + 1 == numQuestions) {
            nextButton.text = "SUBMIT"
        }
    }

    private fun processAnswer(it: View?): Boolean {
        //val result = binding.questionRadioGroup.checkedRadioButtonId
        //firstButton is selected
        var answerIndex = 0
//        when (result) {
//            R.id.answer2 -> answerIndex = 1
//            R.id.answer3 -> answerIndex = 2
//            R.id.answer4 -> answerIndex = 3
//        }
        if (answers[answerIndex] == currentQuestion.answers[0]) {
            return true
        } else {
            Toast.makeText(this.activity, "Selected answer is wrong", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun showQuestion() {
        questionIndex++
        val index = questionIndex
        val questionTextStr = "$index. " + currentQuestion.text

        questionText.text = questionTextStr
        answer1.text = answers[0]
        answer2.text = answers[1]
        answer3.text = answers[2]
        answer4.text = answers[3]
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        showQuestion()
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        //questionIndex = 0
        setQuestion()
    }

    private fun registerListeners(view: View) {
        nextButton.setOnClickListener {
            val snack = Snackbar.make(it, "Next button pressed", Snackbar.LENGTH_SHORT)
            snack.show()
            //findNavController().navigate(R.id.action_quizFragment_to_endQuizFragment)
            if (questionIndex < numQuestions) {
//                Show next question
                it.findNavController().navigate(R.id.action_quizFragment_self)
                //setQuestion()

            } else {
                //End of the test
                if (questionIndex == numQuestions) {
                    questionIndex = 0
                    it.findNavController().navigate(R.id.action_quizFragment_to_endQuizFragment)
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment quizFragment.
         */

        var questionIndex = 0

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}