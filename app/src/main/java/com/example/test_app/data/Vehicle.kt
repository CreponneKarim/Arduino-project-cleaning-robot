package com.example.test_app.data

import android.util.JsonWriter
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.test_app.bind.MutableStateFloatAdapter
import com.google.gson.TypeAdapter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken

data class Vehicle(
    @SerializedName("coords")
    @Expose
    var coords: Coords = Coords(0.0f,0.0f),
    @SerializedName("centralVect")
    @Expose
    var centralVect: Coords=Coords(0.0f,1.0f),
    @SerializedName("leftVect")
    @Expose
    var leftVect: Coords=Coords(-1.0f,0.0f),
    @SerializedName("rightVect")
    @Expose
    var rightVect: Coords=Coords(1.0f,0.0f),
    @SerializedName("speed")
    @JsonAdapter(MutableStateFloatAdapter::class)
    @Expose
    var speed: MutableState<Float> = mutableStateOf( 12.2f),
    @SerializedName("cleanWaterLevel")
    @JsonAdapter(MutableStateFloatAdapter::class)
    @Expose
    var cleanWaterLevel:MutableState<Float> = mutableStateOf( 0f),
    @SerializedName("badWaterLevel")
    @JsonAdapter(MutableStateFloatAdapter::class)
    @Expose
    var badWaterLevel:MutableState<Float> = mutableStateOf( 0f),
    @SerializedName("batteryLevel")
    @JsonAdapter(MutableStateFloatAdapter::class)
    @Expose
    var batteryLevel: MutableState<Float> = mutableStateOf( 0f)
){
    fun copyData(v:Vehicle){
        this.speed.value = v.speed.value
        this.badWaterLevel.value = v.badWaterLevel.value
        this.cleanWaterLevel.value = v.cleanWaterLevel.value
        this.batteryLevel.value = v.batteryLevel.value
        this.leftVect = v.leftVect
        this.centralVect = v.centralVect
        this.rightVect = v.rightVect
        this.coords = v.coords
    }

    fun getVectorOf(sensorId: UltraSonicSensorsIds):Coords{
        return when(sensorId){
            UltraSonicSensorsIds.LEFT   ->leftVect
            UltraSonicSensorsIds.MIDDLE ->centralVect
            UltraSonicSensorsIds.RIGHT  ->rightVect
        }
    }
}
