package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range

@Schema(title = "Location")
data class LocationDto(

    @field:NotNull
    @field:Range(min = -90, max = 90)
    var latitude: Double?,

    @field:NotNull
    @field:Range(min = -180, max = 180)
    var longitude: Double?
)
