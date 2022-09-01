package com.example.challengeyape.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel (
    val name: String,
    val description: String,
    val photo: String,
    val coordinates: CoordinatesModel,
    val ingredients: List<String>
): Parcelable