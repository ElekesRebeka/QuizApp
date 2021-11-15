package com.example.quizapp.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.questions
import com.example.quizapp.utility.DataAdapter
import com.example.quizapp.utility.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestionListFragment : Fragment(), DataAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: DataAdapter
    private lateinit var recyclerView: RecyclerView
    //private lateinit var detailsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    //private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout =  inflater.inflate(R.layout.fragment_question_list, container, false)
        Log.d("xxx", "ListFragment - onCreateView")



        // connect RecyclerView to the adapter
        adapter = DataAdapter(viewModel.list, this)
        recyclerView = layout.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

//        layout?.apply {
//            initializeView(this)
//            registerListeners(this)
//        }

        return layout
    }

//    private fun registerListeners(view: View) {
//        detailsButton.setOnClickListener{
//            findNavController().navigate(R.id.action_questionListFragment_to_quizDetailsFragment)
//        }
//    }
//
//    private fun initializeView(view: View) {
//        detailsButton = view.findViewById(R.id.)
//    }

    override fun onDetailsClick(position: Int) {
        //viewModel.adapterCurrentPosition = position
        findNavController().navigate(R.id.action_questionListFragment_to_quizDetailsFragment)
    }

    override fun onDeleteClick(position: Int) {
        questions.removeAt(position)
        if (!(position < 0 || position >= questions.size)) {
            questions.removeAt(position)
            //adapter.notifyItemRemoved(position)
            recyclerView.adapter?.notifyItemRemoved(position)
        }
    }

//    override fun onItemClick(position: Int) {
//        viewModel.currentPosition = position
//        //findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//        Log.d("xxx", "AdapterPosition: $position")
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}