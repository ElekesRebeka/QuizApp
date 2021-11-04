package com.example.quizapp.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
 * Use the [QuestionAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionAddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var question: EditText
    private lateinit var answer1: EditText
    private lateinit var answer2: EditText
    private lateinit var answer3: EditText
    private lateinit var answer4: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun registerListeners(view: View) {
        addButton.setOnClickListener{
            val list: List<String> = listOf(answer1.text.toString(),answer2.text.toString(),answer3.text.toString(),answer4.text.toString())
            val q1 = Question(question.text.toString(),list)
            questions.add(q1)
            val snack = Snackbar.make(it,"Question added to database",Snackbar.LENGTH_SHORT)
            snack.show()
            //findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }
    }

    private fun initializeView(view: View) {
        addButton = view.findViewById(R.id.addButton)
        question = view.findViewById(R.id.question)
        answer1 = view.findViewById(R.id.answer1)
        answer2 = view.findViewById(R.id.answer2)
        answer3 = view.findViewById(R.id.answer3)
        answer4 = view.findViewById(R.id.answer4)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_question_add, container, false)
        view?.apply {
            initializeView(this)
            registerListeners(this)
        }
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionAddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionAddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}