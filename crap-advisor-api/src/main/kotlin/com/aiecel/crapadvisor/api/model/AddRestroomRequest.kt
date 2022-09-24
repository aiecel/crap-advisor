package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.model.Position
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AddRestroomRequest(

    @field:NotBlank
    val name: String?,

    @field:NotNull
    @field:Valid
    val position: Position?
)