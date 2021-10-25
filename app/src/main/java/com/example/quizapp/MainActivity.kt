package com.example.quizapp

import android.R.attr
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.util.*
import android.R.attr.data
import android.text.Editable
import android.widget.TextView
import android.R.attr.data
import java.io.InputStream
import android.widget.Toast
import android.R.attr.data

const  val TAG:String = "MainActivity"
const  val USERNAME_EXTRA:String = "Username"

class MainActivity : AppCompatActivity() {

    private lateinit var playerName: EditText
    private lateinit var startButton: Button
    private lateinit var contactButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initializeView()
//        registerListeners()
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
}