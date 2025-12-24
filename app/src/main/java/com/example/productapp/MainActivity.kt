package com.example.productapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var productList: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewProducts)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        listView.adapter = adapter

        viewModel.products.observe(this) { products ->
            productList = products
            adapter.clear()
            adapter.addAll(
                products.map { "${it.name} | ${it.quantity} шт. | ${it.price} ₽" }
            )
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val product = productList[position]
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra("productId", product.id)
            intent.putExtra("name", product.name)
            intent.putExtra("quantity", product.quantity)
            intent.putExtra("price", product.price)
            startActivity(intent)
        }

        findViewById<android.widget.Button>(R.id.btnAddProduct)
            .setOnClickListener {
                startActivity(Intent(this, AddProductActivity::class.java))
            }
    }
}