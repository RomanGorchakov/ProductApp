package com.example.productapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlin.concurrent.thread

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: ProductDao =
        AppDatabase.getDatabase(application).productDao()

    val products: LiveData<List<Product>> = dao.getAllProducts()

    fun addProduct(product: Product) {
        thread {
            dao.insert(product)
        }
    }

    fun updateProduct(product: Product) {
        thread {
            dao.update(product)
        }
    }

    fun deleteProduct(product: Product) {
        thread {
            dao.delete(product)
        }
    }
}
