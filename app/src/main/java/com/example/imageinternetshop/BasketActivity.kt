package com.example.imageinternetshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imageinternetshop.lists.Basket
import com.example.imageinternetshop.lists.BasketAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BasketActivity : AppCompatActivity() {

    private lateinit var toolbarBasket: Toolbar
    private lateinit var basketRV: RecyclerView
    private lateinit var totalPriceTV: TextView
    private lateinit var basketFloatingBTN: FloatingActionButton
    private lateinit var basketAdapter: BasketAdapter
    private var totalPrice = 0.0
    private var basket: ArrayList<Basket>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_basket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        setSupportActionBar(toolbarBasket)

        getBasket()

        setAdapter()

        getTotalPrice()

        basketFloatingBTN.setOnClickListener {
            val intent = Intent(this, TotalActivity::class.java)
            intent.putExtra("total", ArrayList(basket!!))
            startActivity(intent)
        }
    }

    private fun setAdapter() {
        basketRV.layoutManager = LinearLayoutManager(this)
        basketAdapter = BasketAdapter(basket!!) { item ->
            removeItemFromBasket(item)
        }
        basketRV.adapter = basketAdapter
    }

    private fun getBasket() {
        val intent = intent
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey("basket")) {
            basket?.addAll(bundle.getSerializable("basket") as ArrayList<Basket>)
        }
    }

    private fun init() {
        toolbarBasket = findViewById(R.id.toolbarBasket)
        basketRV = findViewById(R.id.basketRV)
        totalPriceTV = findViewById(R.id.totalPriceTV)
        basketFloatingBTN = findViewById(R.id.basketFloatingBTN)
    }

    @SuppressLint("SetTextI18n")
    private fun getTotalPrice() {
        for (item in basket!!) {
            totalPrice += item.price
        }
        totalPriceTV.text = "Итого: $totalPrice руб."
    }

    @SuppressLint("SetTextI18n")
    private fun removeItemFromBasket(item: Basket) {
        basketAdapter.removeItem(item)
        totalPrice -= item.price
        totalPriceTV.text = "Итого: $totalPrice руб."
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
