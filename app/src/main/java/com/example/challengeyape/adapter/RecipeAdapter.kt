package com.example.challengeyape.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeyape.R
import com.example.challengeyape.model.RecipeModel

class RecipeAdapter(
    private val recipeList: List<RecipeModel>,
    private val onClickListener: (RecipeModel) -> Unit
) : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(layoutInflater.inflate(R.layout.item_recipe, parent, false))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.render(recipeList[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}