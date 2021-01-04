package net.sherafatpour.shop.view.activity

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import net.sherafatpour.shop.R
import net.sherafatpour.shop.repository.Repository

class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
lateinit var navController:NavController
    lateinit var appbar: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

navView.setOnNavigationItemSelectedListener(this)
        // Passing each menu ID as a set of Ids because each
        supportActionBar?.hide()
        appbar = AppBarConfiguration.Builder(R.navigation.mobile_navigation).build()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appbar)
        NavigationUI.setupWithNavController(navView, navController)
        navView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                navController.navigate(R.id.navigation_home)
            }

            R.id.navigation_card -> {

                val userid = Repository.isLogin(applicationContext)
                if (userid == "notFind") {
                    navController.navigate(R.id.loginFragment)
                } else {
                    navController.navigate(R.id.navigation_card)
                }

            }
            R.id.navigation_profile -> {
                val userid = Repository.isLogin(applicationContext)
                if (userid == "notFind") {
                    navController.navigate(R.id.loginFragment)
                } else {
                    navController.navigate(R.id.navigation_profile)
                }

            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null as DrawerLayout)
    }
}