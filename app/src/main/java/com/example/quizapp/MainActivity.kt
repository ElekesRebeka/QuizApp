package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.util.*


const  val TAG:String = "MainActivity"
const  val USERNAME_EXTRA:String = "Username"

class MainActivity : AppCompatActivity() {

    private lateinit var playerName: EditText
    private lateinit var startButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        registerListeners()
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy()")
    }

    private fun registerListeners(){
        startButton.setOnClickListener{
            val snack = Snackbar.make(it,"Start button pressed",Snackbar.LENGTH_LONG)
            snack.show()

            Log.i(TAG,playerName.text.toString())
            val intent = Intent(this, DisplayMessageActivity::class.java).apply{
                putExtra(USERNAME_EXTRA,playerName.text.toString())
            }
            startActivity(intent)
        }
    }

    private fun initializeView(){
        playerName = findViewById(R.id.playerNameInput)
        startButton = findViewById(R.id.startButton)

    }
}