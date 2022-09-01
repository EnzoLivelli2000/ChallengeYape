package com.example.challengeyape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.challengeyape.databinding.ActivityIngredientsBinding

class IngredientsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIngredientsBinding
    private var ingredients: ArrayList<String> = java.util.ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_ingredients)
        onCreateListView()
    }

    private fun onCreateListView() {
        val bundle = intent.extras
        ingredients.addAll(bundle?.getStringArrayList("INTENT_INGREDIENTS")!!)
        binding.lvIngredients.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients)

        binding.tvQuantityIngredients.text = ingredients.size.toString()
    }
}