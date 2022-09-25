package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.model.Location
import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime

@Schema(title = "Restroom Info")
data class RestroomDto(

    @Schema(title = "Id")
    val id: Long,

    @Schema(title = "Name")
    val name: String?,

    @Schema(title = "Rating")
    val rating: Double?,

    @Schema(title = "Creation datetime")
    val created: ZonedDateTime,

    @Schema(title = "Restroom coordinates")
    val location: Location
)