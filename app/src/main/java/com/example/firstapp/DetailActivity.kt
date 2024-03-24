package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.model.Product
import com.example.firstapp.ProductAdapter
import android.view.View
import android.widget.Button

class DetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val categoryName = intent.getStringExtra("ITEM_Name")

        recyclerView = findViewById(R.id.detailRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Sample data for demonstration
    /*    val products = listOf(
            Product(1, "Women's T-Shirt", "Comfortable cotton t-shirt", 19.99, R.drawable.womens_tshirt),
            Product(2, "Women's Jeans", "Stylish denim jeans", 39.99, R.drawable.womens_jeans)
            // Add more products as needed
        )*/

        val productList = when(categoryName) {
            "Male Fashion" -> listOf(
                Product(11, "Men's Sherwani", "Partywear Sherwani", 2119.00, R.drawable.men_sherwani),
                Product(12, "Men's Shirt", "Comfortable formal shirt", 1199.00, R.drawable.men_shirt),
                Product(13, "Men's shoes", "Comfortable shoes", 3099.00, R.drawable.men_shoes),
                Product(14, "Men's Jeans", "Comfortable denim jeans", 2099.00, R.drawable.men_jeans),
                Product(15, "Men's T-Shirt", "Comfortable cotton t-shirt", 1299.00, R.drawable.men_tshirt)
            )
            "Female Fashion" -> listOf(
                Product(21, "Women's Jeans", "Comfortable Denim jeans", 1119.00, R.drawable.womens_jeans),
                Product(22, "Women's Jeans", "Stylish Denim jeans", 1299.00, R.drawable.woman_jeans1),
                Product(23, "Women's Jeans", "Fashionable jeans", 1219.00, R.drawable.woman_jeans2),
                Product(24, "Women's T-Shirt", "Comfortable cotton t-shirt", 419.00, R.drawable.womens_tshirt),
                Product(25, "Women's Saree", "Beautiful Saree", 2019.00, R.drawable.woman_saree1),
                Product(26, "Women's Saree", "Traditional Saree", 2299.00, R.drawable.woman_saree2),
                Product(27, "Women's T-Shirt", "Comfortable cotton t-shirt", 399.00, R.drawable.woman_tshirt1),
                Product(28, "Women's T-Shirt", "Comfortable cotton t-shirt", 499.00, R.drawable.woman_tshirt2),
                Product(29, "Women's Shoes", "Stylish and comfortable shoes", 1599.00, R.drawable.woman_shoes)

            )
            "Kids Fashion" -> listOf(
                Product(31, "Kid's suit", "Comfortable boys suit", 599.00, R.drawable.boys_dress1),
                Product(32, "Kid's dress", "Stylish boys dress", 589.00, R.drawable.boys_dress2),
                Product(33, "Kid's dress", "Stylish boys partywear", 599.00, R.drawable.boys_dress3),
                Product(34, "Kid's Tshirt", "Comfortable Boys T-shirt", 229.00, R.drawable.boys_tshirt),
                Product(35, "Girl's dress", "Girl's beautiful lehnga", 1239.00, R.drawable.girls_dress1),
                Product(36, "Girl's dress", "Girl's partywear frock", 839.00, R.drawable.girls_frock),
                Product(37, "Girl's dress", "Girl's partywear frock", 949.00, R.drawable.girls_frock2)



            )
            "Misc" -> listOf(
                Product(41, "Bedsheet", "Silk Bedsheet with 2 pillow cover", 899.00, R.drawable.home1),
                Product(42, "Runner", "Beautiful Runner for bedside", 719.00, R.drawable.home2),
                Product(43, "Bedsheet", "Cotton bedsheet with 2 pillow", 699.00, R.drawable.home3),
                Product(44, "Bedsheet", "Beautiful 5 piece bedsheet with blanket", 1119.00, R.drawable.home4),
                Product(45, "Sofa Fabric", "Thick Sofa cover fabric", 1199.00, R.drawable.image1),
                Product(46, "Curtain Fabric", "Blackout curtain's fabric", 2019.00, R.drawable.image2)

            )
            else -> listOf()
        }

        productAdapter = ProductAdapter(productList){ product ->
            openPaymentView(product.name, product.price)
        }
        recyclerView.adapter = productAdapter



    }


    private fun openPaymentView(name: String, price: Double) {
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra("ITEM_PRICE", price)
        intent.putExtra("ITEM_NAME", name)
        startActivity(intent)
    }

}
