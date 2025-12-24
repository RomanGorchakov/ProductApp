package com.example.productapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddProductActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val nameField = findViewById<EditText>(R.id.editName)
        val quantityField = findViewById<EditText>(R.id.editQuantity)
        val priceField = findViewById<EditText>(R.id.editPrice)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val product = Product(
                name = nameField.text.toString(),
                quantity = quantityField.text.toString().toInt(),
                price = priceField.text.toString().toDouble()
            )
            viewModel.addProduct(product)
            finish()
        }
    }
}
