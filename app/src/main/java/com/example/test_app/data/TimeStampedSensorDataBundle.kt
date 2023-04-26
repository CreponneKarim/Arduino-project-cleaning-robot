package com.example.test_app.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TimeStampedSensorDataBundle(
    @SerializedName("sensor")
    @Expose
    val sensor: UltraSonicSensorsIds = UltraSonicSensorsIds.MIDDLE,
    @SerializedName("dataBundle")
    @Expose
    //  key = time, value = measured distance
    val distances: MutableMap<String,DistancePack> = mutableMapOf(),
    //  key = time, value = measured distance
    var coordinates: MutableMap<String,Coords> = mutableMapOf(),
){

    //  add to bundle and
    fun updateDataBundle(newDistances : MutableMap<String,DistancePack>){

        val vect = DataSpace.fullPreviousData.vehicle.getVectorOf(this.sensor)
        newDistances.forEach {
            //  update distances
            this.distances[it.key] = it.value

            //  update the coordinates
            this.coordinates[it.key] = this.calcCoordinates(vect,it.value.Distance.value)
        }
    }
    fun calcCoordinates(vector: Coords,distance:Float):Coords{
            return  Coords(
                vector.x * distance,
                vector.y * distance
            )
    }
    fun offsetDistances(offset:Coords){
        this.distances.forEach{
            distances[it.key]!!.Distance.value+=offset.module()
        }
    }
    fun offsetCoords(offset:Coords){
        this.coordinates.forEach{
            this.coordinates[it.key] = it.value + offset
        }
    }
}
