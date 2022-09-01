package com.example.challengeyape.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeyape.databinding.ItemRecipeBinding
import com.example.challengeyape.model.RecipeModel

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val binding = ItemRecipeBinding.bind(view)

    fun render(recipeModel: RecipeModel, onClickListener:(RecipeModel) -> Unit){
        binding.tvRecipeName.text = recipeModel.name
        Glide.with(binding.ivRecipe.context).load(recipeModel.photo).into(binding.ivRecipe)

        //Lambda Function
        itemView.setOnClickListener{onClickListener(recipeModel)}
    }
}