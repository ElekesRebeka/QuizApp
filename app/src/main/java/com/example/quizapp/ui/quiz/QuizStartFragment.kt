package com.example.quizapp.ui.quiz

import android.os.Bundle
import android.provider.ContactsContract
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
import com.google.android.material.snackbar.Snackbar
import com.example.quizapp.activityResult.PickContact

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizStartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var playerName: EditText
    private lateinit var startButton: Button
    private lateinit var contactButton: Button

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
        val view = inflater.inflate(R.layout.fragment_start_quiz, container, false)

        view?.apply {
            initializeView(this)
            registerListeners(this)
        }

        return view
    }

    private val resultLauncher = registerForActivityResult(PickContact()) {
        val cursor = requireActivity().contentResolver.query(it!!, null, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val chosenName = cursor?.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                Log.i(TAG, "Name: $chosenName")

                val editText = view?.findViewById<EditText>(R.id.playerNameInput)
                editText?.setText(chosenName)
            }
        }
    }


    private fun registerListeners(view: View) {
        startButton.setOnClickListener{
            val snack = Snackbar.make(it,"Start button pressed",Snackbar.LENGTH_SHORT)
            snack.show()
            Log.i(TAG,playerName.text.toString())
            findNavController().navigate(R.id.action_startFragment_to_quizFragment)
        }

        contactButton.setOnClickListener{
            resultLauncher.launch(0)
        }
    }

    private fun initializeView(view: View) {
        playerName = view.findViewById(R.id.playerNameInput)
        startButton = view.findViewById(R.id.startButton)
        contactButton = view.findViewById(R.id.contactButton)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizStartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}