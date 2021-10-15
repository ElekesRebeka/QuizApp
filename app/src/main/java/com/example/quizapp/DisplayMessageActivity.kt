package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast


class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val message = intent.getStringExtra(USERNAME_EXTRA)

        val textView = findViewById<TextView>(R.id.userNameTextView).apply {
            text = message
        }
        //Log.i(TAG,textView.toString())
    }
}