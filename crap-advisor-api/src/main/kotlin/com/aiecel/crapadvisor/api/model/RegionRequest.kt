package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range

data class RegionRequest(

    @field:Parameter(required = true)
    @field:NotNull
    @field:Range(min = -90, max = 90)
    val fromLatitude: Double?,

    @field:Parameter(required = true)
    @field:NotNull
    @field:Range(min = -180, max = 180)
    val fromLongitude: Double?,

    @field:Parameter(required = true)
    @field:NotNull
    @field:Range(min = -90, max = 90)
    val toLatitude: Double?,

    @field:Parameter(required = true)
    @field:NotNull
    @field:Range(min = -180, max = 180)
    val toLongitude: Double?
)
