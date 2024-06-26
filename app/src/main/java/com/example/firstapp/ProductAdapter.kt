package com.example.firstapp
import android.util.Log
import android.view.LayoutInflater
import com.example.firstapp.data.model.Product
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.model.Item

class ProductAdapter(private val productList: List<Product>, private val onProductClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var newProductList: List<Product> = ArrayList(productList)

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.product_name)
        private val description: TextView = view.findViewById(R.id.product_description)
        private val price: TextView = view.findViewById(R.id.product_price)
        private val image: ImageView = view.findViewById(R.id.product_image)

        fun bind(product: Product) {
            name.text = product.name
            description.text = product.description
            price.text = "Price: ${product.price}"
            image.setImageResource(product.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = newProductList[position]
        holder.itemView.setOnClickListener {
            onProductClick(product)
        }
        holder.bind(newProductList[position])
    }

    override fun getItemCount() = newProductList.size
    fun filterList(filteredList: List<Product>) {
          Log.d("ProductAdapter", "FilterList called $filteredList, item count: ${filteredList.size}")

        newProductList = filteredList
        notifyDataSetChanged()
    }
}
