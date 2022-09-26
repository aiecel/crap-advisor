package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.model.Location
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Schema(title = "Add Restroom Request")
data class AddRestroomRequest(

    @field:NotBlank
    @Schema(title = "Name of the restroom", required = true)
    val name: String?,

    @field:NotNull
    @field:Valid
    @Schema(title = "Restroom coordinates", required = true)
    val location: Location?
)