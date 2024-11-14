package com.example.imageinternetshop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val onAddToBasketClick: (Product) -> Unit,
    private val onImageClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Product> = Product.list

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImageIV: ImageView = itemView.findViewById(R.id.productImageIV)
        val productNameTV: TextView = itemView.findViewById(R.id.productNameTV)
        val productPriceTV: TextView = itemView.findViewById(R.id.productPriceTV)
        val toBasketIV: ImageView = itemView.findViewById(R.id.toBasketIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_recycler_list, parent, false)
        return ProductViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productImageIV.setImageResource(product.image)
        holder.productNameTV.text = product.name
        holder.productPriceTV.text = product.price.toString() + " рублей"

        holder.toBasketIV.setOnClickListener {
            onAddToBasketClick(product)
        }

        holder.productImageIV.setOnClickListener {
            onImageClick(product)
        }

        holder.itemView.setOnClickListener(null)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}