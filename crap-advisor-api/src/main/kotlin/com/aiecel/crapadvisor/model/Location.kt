package com.aiecel.crapadvisor.model

import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.Embeddable
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Embeddable
@Schema(title = "Geographic coordinates")
class Location(

    @field:NotNull
    @field:Min(-90)
    @field:Max(90)
    @Schema(title = "Latitude")
    var latitude: Double = 0.0,

    @field:NotNull
    @field:Min(-180)
    @field:Max(180)
    @Schema(title = "Longitude")
    var longitude: Double = 0.0
)