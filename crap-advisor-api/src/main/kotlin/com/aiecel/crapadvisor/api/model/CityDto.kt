package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime

@Schema(title = "City Info")
data class CityDto(

    @Schema(title = "ID")
    val id: Long,

    @Schema(title = "Name")
    val name: String,

    @Schema(title = "Creation datetime")
    val created: ZonedDateTime,

    @Schema(title = "City coordinates")
    val location: LocationDto,

    @Schema(title = "Number of restrooms in the city")
    val restrooms: Long
)
