package com.example.imageinternetshop

import android.annotation.SuppressLint
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
import com.example.imageinternetshop.lists.TotalAdapter

class TotalActivity : AppCompatActivity() {

    private lateinit var toolbarTotal: Toolbar
    private lateinit var totalRV: RecyclerView
    private lateinit var totalAdapter: TotalAdapter
    private lateinit var totalPriceTotalTV: TextView
    private var basket: ArrayList<Basket>? = ArrayList()
    private var totalPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_total)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        setSupportActionBar(toolbarTotal)

        getBasket()

        setAdapter()

        getTotalPrice()
    }

    private fun init() {
        toolbarTotal = findViewById(R.id.toolbarTotal)
        totalRV = findViewById(R.id.totalRV)
        totalPriceTotalTV = findViewById(R.id.totalPriceTotalTV)
    }

    @SuppressLint("SetTextI18n")
    private fun getTotalPrice() {
        for (item in basket!!) {
            totalPrice += item.price
        }
        totalPriceTotalTV.text = "Итого: $totalPrice руб."
    }

    private fun setAdapter() {
        totalRV.layoutManager = LinearLayoutManager(this)
        totalAdapter = TotalAdapter(basket!!)
        totalRV.adapter = totalAdapter
    }

    private fun getBasket() {
        val intent = intent
        val bundle = intent.extras
        if (bundle != null && bundle.containsKey("total")) {
            basket?.addAll(bundle.getSerializable("total") as ArrayList<Basket>)
        }
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