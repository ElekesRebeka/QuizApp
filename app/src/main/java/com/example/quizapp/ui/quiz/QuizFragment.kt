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
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.TAG
import com.example.quizapp.models.Question
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

    lateinit var binding: FragmentQuestionBinding

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>

    private var questionIndex = 1
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
        }

        return view
    }

    private fun registerListeners(view: View) {
        nextButton.setOnClickListener{
            val snack = Snackbar.make(it,"Next button pressed", Snackbar.LENGTH_SHORT)
            snack.show()
            findNavController().navigate(R.id.action_quizFragment_to_endQuizFragment)
        }
    }

    private fun initializeView(view: View) {
        nextButton = view.findViewById(R.id.nextButton)
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