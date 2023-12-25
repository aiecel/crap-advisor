package com.aiecel.crapadvisor.model.dto

import com.aiecel.crapadvisor.model.Location

data class GeocodedCity(
    val name: String,
    val location: Location
)
