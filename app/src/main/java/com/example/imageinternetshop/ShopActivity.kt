package com.example.imageinternetshop

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShopActivity : AppCompatActivity() {

    private lateinit var toolbarShop: Toolbar
    private lateinit var shopRV: RecyclerView
    private lateinit var basketFloatingBTN: FloatingActionButton
    private val basket = mutableListOf<Product>()
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()
        setSupportActionBar(toolbarShop)

        val adapter = ProductAdapter(
            onAddToBasketClick = { product -> addToBasket(product) },
            onImageClick = { product -> scaleTo(product) }
        )

        shopRV.layoutManager = LinearLayoutManager(this)
        shopRV.adapter = adapter

        if (count > 0) basketFloatingBTN.visibility = View.VISIBLE


        basketFloatingBTN.setOnClickListener {
            startActivity(Intent(this, BasketActivity::class.java))
        }
    }

    private fun init() {
        toolbarShop = findViewById(R.id.toolbarShop)
        shopRV = findViewById(R.id.shopRV)
        basketFloatingBTN = findViewById(R.id.basketFloatingBTN)
    }

    private fun scaleTo(product: Product) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_image)
        val imageView = dialog.findViewById<ImageView>(R.id.dialogImageView)
        imageView.setImageResource(product.image)
        dialog.show()

        imageView.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun addToBasket(product: Product) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.add_basket_dialog)

        val yesButton = dialog.findViewById<Button>(R.id.yesBTN)
        val noButton = dialog.findViewById<Button>(R.id.noBTN)

        yesButton.setOnClickListener {
            basket.add(product)
            count++
            Toast.makeText(this, "$count", Toast.LENGTH_LONG).show()
            basketFloatingBTN.visibility = View.VISIBLE
            createBadge()
            dialog.dismiss()
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun createBadge() {
        val badge: BadgeDrawable = BadgeDrawable.create(this).apply {
            number = count
            isVisible = true
        }

        BadgeUtils.attachBadgeDrawable(badge, basketFloatingBTN)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shop, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuShopExit -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}