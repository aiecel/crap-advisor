package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(title = "Add Restroom Request")
data class AddRestroomRequest(

    @field:NotBlank
    @field:Size(max = 50)
    @Schema(title = "Name of the restroom", required = true)
    val name: String?,

    @field:NotNull
    @field:Valid
    @Schema(title = "Restroom coordinates", required = true)
    val location: LocationDto?
)
