package com.example.challengeyape.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordinatesModel(val lat: String, val lon: String): Parcelable