package com.example.challengeyape.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val photo: String,
    @SerializedName("coordinates") val coordinates: CoordinatesModel,
    @SerializedName("ingredients") val ingredients: List<String>
) : Parcelable