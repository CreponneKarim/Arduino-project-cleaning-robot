package com.example.test_app.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DistancePack(
    @SerializedName("distance")
    @Expose
    val Distance: MutableState<Float> = mutableStateOf(0.0f),
    @SerializedName("vehicleCoordsAtTime")
    @Expose
    val vehicleCoordsAtTime: Coords = Coords(0.0f,0.0f),
    @SerializedName("directionVect")
    @Expose
    val direction:Coords  = Coords(0.0f,0.0f)
)