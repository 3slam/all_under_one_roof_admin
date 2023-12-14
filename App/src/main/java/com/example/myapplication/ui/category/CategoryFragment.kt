package com.example.myapplication.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.CategoryLayoutBinding
import com.example.myapplication.uitiles.collect
import com.example.myapplication.uitiles.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment :BaseFragment<CategoryLayoutBinding>(){
    override val layoutFragmentId = R.layout.category_layout
    override val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        collect(viewModel.homeUiState){
            val adapter = ContainerFragmentPagerAdapter(childFragmentManager, it.categories ,
                it.apiToken
                )
            binding.viewPager.adapter = adapter
            binding.tabLayout.setupWithViewPager(binding.viewPager)
         }

        collectLast(viewModel.homeUiEffect){
            when(it){
                is CategoryUiEffect.Back ->  {
                    val action = CategoryFragmentDirections.actionCategoryFragmentToHomeFragment2(it.api)
                    findNavController().navigate(action)
                }
            }
        }
    }
}