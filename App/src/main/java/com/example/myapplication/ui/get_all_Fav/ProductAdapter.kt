package com.example.myapplication.ui.get_all_Fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.data.model.Product
import com.example.myapplication.databinding.ProductInFavBinding
import com.example.myapplication.databinding.ProductItemBinding


class ProductAdapter(
  //  private val listener: OnProductListener

) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    abstract class BaseViewHolder(binding : ViewDataBinding):RecyclerView.ViewHolder(binding.root)
    class ProductViewHolder(val binding: ProductInFavBinding) : BaseViewHolder(binding)

    private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }


    private val differ = AsyncListDiffer(this, diffCallback)

    var products: List<Product>
        get() = differ.currentList
        set(value) = differ.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductInFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = products[position]
        holder.binding.apply {
            textProductCreatedAt.text = currentItem.createdAt
            textProductUpdatedAt.text= currentItem.updatedAt
            textProductDescription.text = currentItem.description
            textProductName.text =currentItem.name
            textProductPrice.text = currentItem.price.toString()
            textProductDiscountedPrice.text = currentItem.priceAfterDiscount.toString()
            image.load(currentItem.url)
        }

//        holder.binding.removeFromFavorite.setOnClickListener {
//            listener.onDeleteClick(currentItem)
//            notifyItemRemoved(position)
//        }

    }
    override fun getItemCount(): Int = products.size

}

interface OnProductListener {
    fun onDeleteClick(item:Product)

}
