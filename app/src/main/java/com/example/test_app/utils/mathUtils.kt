package com.example.test_app.utils

import com.example.test_app.data.Coords
import kotlin.math.cos
import kotlin.math.sin

fun calcCoords(vect : Coords, distance:Int): Coords {
    return Coords(vect.x * distance, vect.y * distance)
}

fun rotateVector(vect: Coords, angle:Double?): Coords {
    if (angle==null)
        return vect

    val angleInRad = Math.toRadians(angle!!)
    val theCos = cos(angleInRad)
    val theSin = sin(angleInRad)
    return Coords(
        (theCos * vect.x - theSin * vect.y) as Float,
        (theSin * vect.x + theCos * vect.y) as Float
    )
}