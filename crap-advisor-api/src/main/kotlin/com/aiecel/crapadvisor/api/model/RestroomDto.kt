package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.model.Position
import java.time.ZonedDateTime

data class RestroomDto(
    val id: Long,
    val name: String?,
    val rating: Double?,
    val created: ZonedDateTime,
    val position: Position
)