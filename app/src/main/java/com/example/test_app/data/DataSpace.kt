package com.example.test_app.data

import android.provider.ContactsContract.RawContacts.Data
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.File

class DataSpace{
    companion object{
        var fullPreviousData: AllData = AllData()
        fun updateData(
            data:AllData
        ){
            //  before updating the vehicle data we need to get the offset
            //  by which the center of the vehicle moved
            var offset = fullPreviousData.vehicle.coords - data.vehicle.coords
            //  recalculate the existing relative coordinates
            //  then
            //  add the new coordinates

            //  we do all of that so when we need it we find it (in the drawing phase)
            fullPreviousData.leftSensorData.apply{
                offsetDistances(offset=offset)
                offsetCoords(offset=offset)
                updateDataBundle(data.leftSensorData.distances)
            }
            fullPreviousData.middleSensorData.apply {
                offsetDistances(offset=offset)
                offsetCoords(offset=offset)
                updateDataBundle(data.middleSensorData.distances)
            }
            fullPreviousData.rightSensorData.apply {
                offsetDistances(offset=offset)
                offsetCoords(offset=offset)
                updateDataBundle(data.rightSensorData.distances)
            }

            //  copy vehicle data
            fullPreviousData.vehicle.copyData(data.vehicle)

        }

    }

    fun storeData():String{
        val gson = Gson()
        //  serialize
        val json = gson.toJson(fullPreviousData).toString()
        File("data.json").bufferedWriter().write(json)
        return json
    }
    //  if no file does not exist or it doesn't contain any data
    //  just return new plain object
    private fun retrieveData(): AllData{
        val file  = File("data.json")
        if(file.exists() && file.readText().trim()!="") {
            val json = file.readText()
            fullPreviousData = Gson().fromJson(
                json,
                AllData::class.java
            )
        }else //  create new object and serialize it and write it to that file
            fullPreviousData= AllData()

        return fullPreviousData
    }

    init {
        val retrievedData = retrieveData()
        fullPreviousData = retrievedData
    }
}