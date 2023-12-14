package com.example.myapplication.ui.category.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerFragment : Fragment(), OnProductListener {

    val viewModel: ProductViewModel by viewModels()
    private lateinit var category: Category
    private lateinit var apiToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getParcelable(ARG_CATEGORY)!!
            apiToken = it.getString(ARG_API)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_category, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val productAdapter = ProductAdapter(this)
        productAdapter.products = category.product
        recyclerView.adapter = productAdapter

       val allProducts =  productAdapter.products
        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               val searched = allProducts.filter { it.name.contains(newText.toString()) }
                productAdapter.products = searched
                return true
            }
        })

        return view
    }

    override fun onAddToFavClick(item: Product) {
        viewModel.addToFavorite(apiToken, item.id)
    }

    override fun onAddToFavCart(item: Product) {
        viewModel.addToCart(apiToken, item.id)
    }

    companion object {
        private const val ARG_CATEGORY = "category"
        private const val ARG_API = "api"

        fun newInstance(category: Category, apiToken: String): ContainerFragment {
            val fragment = ContainerFragment()
            val args = Bundle()
            args.putParcelable(ARG_CATEGORY, category)
            args.putString(ARG_API, apiToken)
            fragment.arguments = args
            return fragment
        }
    }


}
