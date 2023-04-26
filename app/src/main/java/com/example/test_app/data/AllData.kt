package com.example.test_app.data

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllData(
    @SerializedName("middleSensorData")
    @Expose
    var middleSensorData:TimeStampedSensorDataBundle = TimeStampedSensorDataBundle(
        sensor = UltraSonicSensorsIds.MIDDLE
    ),
    @SerializedName("leftSensorData")
    @Expose
    var leftSensorData:TimeStampedSensorDataBundle = TimeStampedSensorDataBundle(
        sensor = UltraSonicSensorsIds.LEFT
    ),
    @SerializedName("rightSensorData")
    @Expose
    var rightSensorData:TimeStampedSensorDataBundle = TimeStampedSensorDataBundle(
        sensor = UltraSonicSensorsIds.RIGHT
    ),
    @SerializedName("vehicle")
    @Expose
    var vehicle: Vehicle = Vehicle(),
){

}
