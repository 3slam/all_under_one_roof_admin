package com.example.myapplication.ui.get_all_in_cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.CartFargmentBinding
import com.example.myapplication.databinding.FavoriteFragmentBinding
import com.example.myapplication.uitiles.collect
import com.example.myapplication.uitiles.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<CartFargmentBinding>()  {


    override val layoutFragmentId = R.layout.cart_fargment
    private lateinit var productIncartAdapter: ProductInCartAdapter
    override val viewModel : CartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        productIncartAdapter = ProductInCartAdapter( )
        binding.rv.apply {
            adapter = productIncartAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collect(viewModel.productUiState){
         productIncartAdapter.products = it.products
        }

        collectLast(viewModel.productUiEffect){effect->
             when(effect) {
                 is CartUiEffect.Back -> {
                     val action = CartFragmentDirections.actionCartFragmentToHomeFragment2(effect.api)
                     findNavController().navigate(action)
                 }
             }
            }
        }




}