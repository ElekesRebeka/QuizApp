package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
        lateinit var mHandler: Handler
        lateinit var mRunnable: Runnable
        startButton.setOnClickListener{
            val snack = Snackbar.make(it,"Start button pressed",Snackbar.LENGTH_SHORT)
            snack.show()
            mRunnable = Runnable {
                Log.i(TAG,playerName.text.toString())
                val intent = Intent(this, DisplayMessageActivity::class.java).apply{
                    putExtra(USERNAME_EXTRA,playerName.text.toString())
                }
                startActivity(intent)
            }
            mHandler = Handler()
            mHandler.postDelayed(mRunnable, 1000)
        }
    }

    private fun initializeView(){
        playerName = findViewById(R.id.playerNameInput)
        startButton = findViewById(R.id.startButton)

    }
}