package com.aiecel.crapadvisor.model.dto

import com.aiecel.crapadvisor.model.Location

data class Region(
    val fromLocation: Location,
    val toLocation: Location,
) {

    val lowerLeftLocation: Location = Location(
        latitude = minOf(fromLocation.latitude, toLocation.latitude),
        longitude = minOf(fromLocation.longitude, toLocation.longitude)
    )

    val upperRightLocation: Location = Location(
        latitude = maxOf(fromLocation.latitude, toLocation.latitude),
        longitude = maxOf(fromLocation.longitude, toLocation.longitude)
    )

    override fun toString(): String {
        return "($fromLocation to $toLocation)"
    }
}
