package com.epalburquerqueiii.aexperience.UI

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.activity_main1.*

class MainActivity1 : AppCompatActivity() {

    private lateinit var navController :NavController
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 4000 //3 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            splash.visibility = View.INVISIBLE
            // Mediante fragment
           //replaceFragment(AutorizadosFragment())
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

     //   setSupportActionBar(toolbar1)

        navController = Navigation.findNavController(this, R.id.main_fragment)
        NavigationUI.setupWithNavController(bottom_nav,navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
        Thread.sleep(500)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction =  supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
