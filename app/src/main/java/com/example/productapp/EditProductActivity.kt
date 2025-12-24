package com.example.productapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class EditProductActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()
    private var productId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val nameField = findViewById<EditText>(R.id.editName)
        val quantityField = findViewById<EditText>(R.id.editQuantity)
        val priceField = findViewById<EditText>(R.id.editPrice)

        productId = intent.getIntExtra("productId", 0)
        nameField.setText(intent.getStringExtra("name"))
        quantityField.setText(intent.getIntExtra("quantity", 0).toString())
        priceField.setText(intent.getDoubleExtra("price", 0.0).toString())

        findViewById<Button>(R.id.btnUpdate).setOnClickListener {
            val updatedProduct = Product(
                id = productId,
                name = nameField.text.toString(),
                quantity = quantityField.text.toString().toInt(),
                price = priceField.text.toString().toDouble()
            )
            viewModel.updateProduct(updatedProduct)
            finish()
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val product = Product(
                id = productId,
                name = nameField.text.toString(),
                quantity = quantityField.text.toString().toInt(),
                price = priceField.text.toString().toDouble()
            )
            viewModel.deleteProduct(product)
            finish()
        }
    }
}
