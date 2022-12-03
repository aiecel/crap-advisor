package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(title = "Geographic coordinates")
data class LocationDto(

    @field:NotNull
    @field:Min(-90)
    @field:Max(90)
    var latitude: Double?,

    @field:NotNull
    @field:Min(-180)
    @field:Max(180)
    var longitude: Double?
)
