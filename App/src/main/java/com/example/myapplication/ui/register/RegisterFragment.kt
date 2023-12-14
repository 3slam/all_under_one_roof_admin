package com.example.myapplication.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.RegisterFragmentBinding

import com.example.myapplication.uitiles.collectLast

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding>() {


    override val layoutFragmentId = R.layout.register_fragment

    override val viewModel : RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        collectLast(viewModel.loginUiEffect){ effect ->
           when(effect){
               is RegisterUiEffect.Register -> {
                   val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment2(effect.apiToken)
                   findNavController().navigate(action)
               }

               RegisterUiEffect.CompleteALlInfo -> {
                   Toast.makeText(requireContext(), "Complete ALl Info PLZ", Toast.LENGTH_SHORT).show()
               }

               RegisterUiEffect.GoToLogIn -> {
                   val action = RegisterFragmentDirections.actionRegisterFragmentToLogInFragment()
                   findNavController().navigate(action)
               }
           }
        }


    }
}