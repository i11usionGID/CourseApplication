package com.i11usion.feature_main.presentation.main

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
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
import com.i11usion.feature_main.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainViewModel
    private val binding by viewBinding(FragmentMainBinding::bind)


    private val adapter by lazy {
        CoursesAdapter { course ->
            viewModel.onFavoriteClick(course.id)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val rvState = binding.rvCourses.layoutManager
            ?.onSaveInstanceState()

        outState.putParcelable(RV_STATE_KEY, rvState)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        val factory = (requireActivity().application as ViewModelFactoryProvider)
            .provideViewModelFactory()

        viewModel = ViewModelProvider(this, factory)
            .get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvCourses.apply {
            adapter = this@MainFragment.adapter
            this.layoutManager = layoutManager
        }

        val savedRvState = savedInstanceState?.getParcelable<Parcelable>(RV_STATE_KEY)
        savedRvState?.let {
            layoutManager.onRestoreInstanceState(it)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.submitList(state.courses) {
                        if (state.needToScroll) {
                            binding.rvCourses.scrollToPosition(0)
                        }
                    }
                }
            }
        }


        binding.llFilter.setOnClickListener {
            viewModel.sortCoursesByDate()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (viewModel.state.value.isSorted) {
                viewModel.getSortedCoursesByDate()
            } else {
                viewModel.getCourses()
            }
        }
    }

    companion object {
        private const val RV_STATE_KEY = "rv_state"
    }
}

