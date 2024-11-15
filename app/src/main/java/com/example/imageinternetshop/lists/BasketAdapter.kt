package com.example.imageinternetshop.lists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imageinternetshop.R

class BasketAdapter(
    private val basketList: ArrayList<Basket>,
    private val onItemClick: (Basket) -> Unit,
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val basketImageIV: ImageView = itemView.findViewById(R.id.basketImageIV)
        private val basketNameTV: TextView = itemView.findViewById(R.id.basketNameTV)
        private val basketPriceTV: TextView = itemView.findViewById(R.id.basketPriceTV)

        @SuppressLint("SetTextI18n")
        fun bind(basketItem: Basket, onItemClick: (Basket) -> Unit) {
            basketImageIV.setImageResource(basketItem.image)
            basketNameTV.text = basketItem.name
            basketPriceTV.text = "${basketItem.price} руб."
            itemView.setOnClickListener { onItemClick(basketItem) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.basket_recycler_list, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketList[position], onItemClick)
    }

    override fun getItemCount() = basketList.size

    fun removeItem(basketItem: Basket) {
        val position = basketList.indexOf(basketItem)
        if (position != -1) {
            basketList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}