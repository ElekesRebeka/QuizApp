package com.example.quizapp.ui.quiz

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quizapp.DisplayMessageActivity
import com.example.quizapp.R
import com.example.quizapp.TAG
import com.example.quizapp.models.Question
import com.example.quizapp.models.questions
import com.google.android.material.snackbar.Snackbar
import androidx.activity.OnBackPressedCallback

import androidx.annotation.NonNull
import kotlin.system.exitProcess


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
const val CORRECT_ANSWERS_EXTRA:String = ""

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
    private lateinit var radioGroup: RadioGroup

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

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
        radioGroup = view.findViewById(R.id.radio_group)
        if (questionIndex + 1 == numQuestions) {
            nextButton.text = "SUBMIT"
        }
    }

    private fun processAnswer(it: View?): Boolean {
        val buttonGroup: RadioGroup = requireView().findViewById(R.id.radio_group)
        val result = buttonGroup.checkedRadioButtonId
        //firstButton is selected
        var answerIndex = 0
        when (result) {
            R.id.answer2 -> answerIndex = 1
            R.id.answer3 -> answerIndex = 2
            R.id.answer4 -> answerIndex = 3
        }
        if (answers[answerIndex] == currentQuestion.answers[0]) {
            correctAnswers++
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
        setQuestion()
    }

    private fun registerListeners(view: View) {
        nextButton.setOnClickListener {
            //val snack = Snackbar.make(it, "Next button pressed", Snackbar.LENGTH_SHORT)
            //snack.show()
            processAnswer(it)
            if (questionIndex < numQuestions) {
                //Show next question
                it.findNavController().navigate(R.id.action_quizFragment_self)
            } else {
                //End of the test
                if (questionIndex == numQuestions) {
                    questionIndex = 0
                    it.findNavController().navigate(R.id.action_quizFragment_to_endQuizFragment)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                showAreYouSureDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }

    fun showAreYouSureDialog(){
        AlertDialog.Builder(activity).apply {
            setTitle("Exit")
            setMessage("Are you sure you want to end this quiz?")

            setPositiveButton("Yes") { _, _ ->
                // if user press yes, then finish the current activity
                findNavController().navigate(R.id.action_quizFragment_to_endQuizFragment)
            }

            setNegativeButton("No"){_, _ ->
                // if user press no, then return the activity
                Toast.makeText(activity, "Thank you",
                    Toast.LENGTH_LONG).show()
            }

            setCancelable(true)
        }.create().show()
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
        var correctAnswers = 0
        const val numQuestions = 3

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