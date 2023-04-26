package com.example.test_app.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.math.sqrt

data class Coords(
    @SerializedName("x")
    @Expose
    var x:Float,
    @SerializedName("y")
    @Expose
    var y:Float
){
    operator fun plus(c:Coords):Coords {
        this.x+= c.x
        this.y+=c.y
        return Coords(
            this.x + c.x,
            this.y + c.y
        )
    }
    operator fun minus(c:Coords):Coords{
        return Coords(
            this.x - c.x,
            this.y - c.y
        )
    }
    fun module():Float{
        return sqrt((x*x + y*y) as Double) as Float
    }
}
