package com.i11usion.feature_main.presentation.favorite

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.i11usion.core.di.ViewModelFactoryProvider
import com.i11usion.core.utils.viewBinding.viewBinding
import com.i11usion.feature_main.R
import com.i11usion.feature_main.databinding.FragmentFavoritesBinding
import com.i11usion.feature_main.presentation.main.CoursesAdapter
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var viewModel: FavoriteViewModel
    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private val adapter by lazy {
        CoursesAdapter { course ->
            viewModel.onFavoriteClick(course)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val factory = (requireActivity().application as ViewModelFactoryProvider)
            .provideViewModelFactory()

        viewModel = ViewModelProvider(this, factory)
            .get(FavoriteViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCourses.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.submitList(state.courses)
                }
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.loadFavorites()
        }
    }
}
