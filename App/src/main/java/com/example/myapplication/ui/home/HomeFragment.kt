package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.HomeFragmentBinding
import com.example.myapplication.uitiles.collect
import com.example.myapplication.uitiles.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {


    override val layoutFragmentId = R.layout.home_fragment
    override val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        collectLast(viewModel.homeUiEffect){
            when(it){
                HomeUiEffect.SignOut -> {
                  findNavController().navigate(R.id.action_homeFragment2_to_registerFragment)
                }

                is HomeUiEffect.GoToCategory -> {
               val action = HomeFragmentDirections.actionHomeFragment2ToCategoryFragment(it.apiToken)
                    findNavController().navigate(action)
                }

                is HomeUiEffect.GoToCart -> {
                    val action = HomeFragmentDirections.actionHomeFragment2ToCartFragment(it.apiToken)
                    findNavController().navigate(action)

                }
                is HomeUiEffect.GoToFav -> {
                    val action = HomeFragmentDirections.actionHomeFragment2ToFavoriteFragment(it.apiToken)
                    findNavController().navigate(action)

                }

            }
        }
    }

}