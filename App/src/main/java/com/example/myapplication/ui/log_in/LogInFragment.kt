package com.example.myapplication.ui.log_in

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.LogInFragmentBinding
import com.example.myapplication.uitiles.collect
import com.example.myapplication.uitiles.collectLast

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<LogInFragmentBinding>() {


    override val layoutFragmentId = R.layout.log_in_fragment

    override val viewModel: LogInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        collectLast(viewModel.loginUiEffect) { effect ->
            when (effect) {

                LogInUiEffect.GoToRegister -> {
                    val action = LogInFragmentDirections.actionLogInFragmentToRegisterFragment()
                    findNavController().navigate(action)
                }

                is LogInUiEffect.LogIn -> {
                    val action =
                        LogInFragmentDirections.actionLogInFragmentToHomeFragment2(effect.apiToken)
                    findNavController().navigate(action)
                }
            }

        }
    }


}