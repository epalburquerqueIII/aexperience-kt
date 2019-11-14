package com.epalburquerqueiii.aexperience.UI

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.epalburquerqueiii.aexperience.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
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
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val i : Int = navView.getMenu().size() -1
        for (a in 4..i) {
            navView.getMenu().getItem(a).setVisible(false)
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Añade menu al fragment
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.eventosFragment,
            R.id.eventosFragment,
            R.id.espaciosUIFragment,
            R.id.loginFragment,
            R.id.usuariosFragment,
            R.id.AutorizadosFragment,
            R.id.ConsumoBonosFragment,
            R.id.reservasFragment,
            R.id.horariosFragment,
            R.id.TipospagosFragment,
            R.id.tiposEventosFragment,
            R.id.bonosFragment,
            R.id.PagosFragment,
            R.id.usuariosrolesFragment,
            R.id.newsletterUIFragment,
            R.id.MenusFragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // Soluciona los problemas de Click
        navView.bringToFront()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
        Thread.sleep(500)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
