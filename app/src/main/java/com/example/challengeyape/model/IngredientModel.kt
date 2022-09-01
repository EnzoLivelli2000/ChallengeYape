package com.example.challengeyape.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientModel
    (
    val id: Int,
    val name: String,
    val quantity: Double
) : Parcelable {

}