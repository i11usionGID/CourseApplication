package com.i11usion.feature_auth.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.i11usion.core.di.ViewModelFactoryProvider
import com.i11usion.core.navigation.LoginNavigation
import com.i11usion.core.ui.viewBinding.viewBinding
import com.i11usion.feature_auth.R
import com.i11usion.feature_auth.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private var navigation: LoginNavigation? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as? LoginNavigation
        val factoryProvider = requireActivity().application as ViewModelFactoryProvider
        viewModelFactory = factoryProvider.provideViewModelFactory()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoginEnabled.collect { enabled ->
                    binding.buttonLogin.isEnabled = enabled
                }
            }
        }

        binding.etLogin.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }

        binding.etPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }

        binding.buttonLogin.setOnClickListener {
            navigation?.onLoginSuccess()
        }
        binding.buttonVK.setOnClickListener {
            openUrl("https://vk.com/")
        }
        binding.buttonOdnoklassniki.setOnClickListener {
            openUrl("https://ok.ru/")
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigation = null
    }

    private fun openUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
    }

}

