package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast


class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)
        val extras = intent.extras;
        var userName: String
        if(extras == null) {
            userName= null.toString();
        } else {
            userName = extras.getString("USERNAME_EXTRA").toString()
        }
        Log.i(TAG,userName)
    }
}