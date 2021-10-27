package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizapp.ui.quiz.QuizStartFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView


const  val TAG:String = "MainActivity"
const  val USERNAME_EXTRA:String = "Username"

class MainActivity : AppCompatActivity() {

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG,"onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        setSupportActionBar(topAppBar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        //supportActionBar?.setDisplayHomeAsUpEnabled(true);
        initMenu()

    }

    private fun initializeView() {
        topAppBar = findViewById(R.id.topAppBar)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
    }

    private fun initMenu(){
        topAppBar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
//            val transaction = supportFragmentManager.beginTransaction()
//            when(menuItem.itemId) {
//                //R.id.home -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
//                //R.id.quiz -> findNavController(R.id.nav_host_fragment).navigate(R.id.startFragment)
//                R.id.home -> transaction.replace(R.id.nav_host_fragment, HomeFragment())
//                R.id.quiz -> transaction.replace(R.id.nav_host_fragment, QuizStartFragment())
//
//            }
//            transaction.commit()

            when(menuItem.itemId){
                R.id.home -> findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                R.id.quiz -> findNavController(R.id.nav_host_fragment).navigate(R.id.startFragment)
            }

            drawerLayout.close()
            true
        }
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