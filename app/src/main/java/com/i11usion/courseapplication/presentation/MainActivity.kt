package com.i11usion.courseapplication.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.i11usion.core.navigation.LoginNavigation
import com.i11usion.courseapplication.R
import com.i11usion.feature_auth.presentation.LoginFragment
import com.i11usion.feature_main.presentation.account.AccountFragment
import com.i11usion.feature_main.presentation.favorite.FavoritesFragment
import com.i11usion.feature_main.presentation.main.MainFragment

class MainActivity : AppCompatActivity(), LoginNavigation {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)

//        if (savedInstanceState == null) {
//            bottomNavigation.visibility = View.GONE
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.mainContainer, LoginFragment())
//                .commit()
//        }
        showMainScreen()
    }

    override fun onLoginSuccess() {
        showMainScreen()
    }


    private fun showMainScreen() {
        bottomNavigation.visibility = View.VISIBLE

        if (supportFragmentManager.findFragmentByTag(MAIN_TAG) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, MainFragment(), MAIN_TAG)
                .commit()
        }

        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener { item ->
            val fm = supportFragmentManager
            val transaction = fm.beginTransaction()

            fm.fragments.forEach { transaction.hide(it) }

            val fragment = when (item.itemId) {
                R.id.menu_main ->
                    fm.findFragmentByTag(MAIN_TAG) ?: MainFragment().also {
                        transaction.add(R.id.mainContainer, it, MAIN_TAG)
                    }

                R.id.menu_favorites ->
                    fm.findFragmentByTag(FAVORITE_TAG) ?: FavoritesFragment().also {
                        transaction.add(R.id.mainContainer, it, FAVORITE_TAG)
                    }

                R.id.menu_account ->
                    fm.findFragmentByTag(ACCOUNT_TAG) ?: AccountFragment().also {
                        transaction.add(R.id.mainContainer, it, ACCOUNT_TAG)
                    }

                else -> null
            }

            fragment?.let { transaction.show(it) }
            transaction.commit()
            true
        }
    }

    companion object {
        private const val MAIN_TAG = "main"
        private const val FAVORITE_TAG = "fav"
        private const val ACCOUNT_TAG = "acc"
    }
}
