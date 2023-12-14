package com.example.myapplication.ui.get_all_in_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.model.Product
import com.example.myapplication.data.model.ProductInCart
import com.example.myapplication.databinding.ProductInCartBinding
import com.example.myapplication.databinding.ProductItemBinding


class ProductInCartAdapter (
   // private val listener: OnProductListener
) : RecyclerView.Adapter<ProductInCartAdapter.ProductViewHolder>() {

    abstract class BaseViewHolder(binding : ViewDataBinding):RecyclerView.ViewHolder(binding.root)
    class ProductViewHolder(val binding: ProductInCartBinding) : BaseViewHolder(binding)

    private val diffCallback = object : DiffUtil.ItemCallback<ProductInCart>() {
        override fun areItemsTheSame(oldItem: ProductInCart, newItem: ProductInCart): Boolean {
            return oldItem.description == newItem.description

        }

        override fun areContentsTheSame(oldItem: ProductInCart, newItem: ProductInCart): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }


    private val differ = AsyncListDiffer(this, diffCallback)

    var products: List<ProductInCart>
        get() = differ.currentList
        set(value) = differ.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductInCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        holder.binding.apply {
            textProductDescription.text = currentItem.description
            textProductName.text =currentItem.name
            textProductPrice.text = currentItem.price.toString()
            textProductDiscountedPrice.text = currentItem.price_after_discount.toString()
        }

//        holder.binding.removeFromCart.setOnClickListener {
//            listener.onDeleteClick(currentItem)
//            notifyItemRemoved(position)
//        }
    }
    override fun getItemCount(): Int = products.size

}
//interface OnProductListener {
//    fun onDeleteClick(item:ProductInCart)
//
//}
