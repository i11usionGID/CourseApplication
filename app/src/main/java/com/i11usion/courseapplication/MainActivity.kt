package com.i11usion.courseapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.i11usion.feature_auth.LoginFragment
import com.i11usion.feature_auth.LoginNavigation
import com.i11usion.feature_main.MainFragment

class MainActivity : AppCompatActivity(), LoginNavigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment())
            .commit()
    }

    override fun onLoginSuccess() {
        openMainScreen()
    }


    fun openMainScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }

}