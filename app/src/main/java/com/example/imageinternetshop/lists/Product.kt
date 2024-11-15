package com.example.imageinternetshop.lists

import com.example.imageinternetshop.R

class Product(val image: Int, val name: String, val price: Double) {
    companion object {
        val list = listOf(
            Product(R.drawable.store, "Магазин", 250.00),
            Product(R.drawable.store, "Снова магазин", 155.00),
            Product(R.drawable.store, "Опять магазин", 150.00),
            Product(R.drawable.store, "И вновь магазин", 350.00),
            Product(R.drawable.store, "И это тоже магазин", 2365.00)
        )
    }
}