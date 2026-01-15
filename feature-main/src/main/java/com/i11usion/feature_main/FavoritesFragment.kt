package com.i11usion.feature_main

import android.util.Log
import androidx.fragment.app.Fragment

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    override fun onResume() {
        super.onResume()
        Log.d("NAV_TEST", "MainFragment shown")
    }

}
