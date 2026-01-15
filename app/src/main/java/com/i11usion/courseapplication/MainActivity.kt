package com.i11usion.courseapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.i11usion.feature_auth.LoginFragment
import com.i11usion.feature_auth.LoginNavigation
import com.i11usion.feature_main.AccountFragment
import com.i11usion.feature_main.FavoritesFragment
import com.i11usion.feature_main.MainFragment

class MainActivity : AppCompatActivity(), LoginNavigation {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)

        if (savedInstanceState == null) {
            bottomNavigation.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, LoginFragment())
                .commit()
        }
    }

    override fun onLoginSuccess() {
        showMainScreen()
    }


    private fun showMainScreen() {
        bottomNavigation.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, MainFragment())
            .commit()

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.menu_main -> MainFragment()
                R.id.menu_favorites -> FavoritesFragment()
                R.id.menu_account -> AccountFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, it)
                    .commit()
                true
            } ?: false
        }
    }
}