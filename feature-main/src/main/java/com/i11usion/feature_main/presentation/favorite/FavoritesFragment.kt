package com.i11usion.feature_main.presentation.favorite

import android.util.Log
import androidx.fragment.app.Fragment
import com.i11usion.feature_main.R

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    override fun onResume() {
        super.onResume()
        Log.d("NAV_TEST", "MainFragment shown")
    }

}