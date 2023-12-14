package com.example.myapplication.ui.get_all_Fav

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FavoriteFragmentBinding
import com.example.myapplication.databinding.ProductFragmentBinding
import com.example.myapplication.uitiles.collect
import com.example.myapplication.uitiles.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FavoriteFragmentBinding>()  {


    override val layoutFragmentId = R.layout.favorite_fragment
    private lateinit var productAdapter: ProductAdapter
    override val viewModel : FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        productAdapter = ProductAdapter()
        binding.rv.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collect(viewModel.productUiState){
         productAdapter.products = it.products
        }

        collectLast(viewModel.productUiEffect){effect->
             when(effect) {
                 is FavoriteUiEffect.Back -> {
                     val action = FavoriteFragmentDirections.actionFavoriteFragmentToHomeFragment2(effect.api)
                     findNavController().navigate(action)
                 }
             }
            }
        }




}