package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime

@Schema(title = "Review Info")
data class ReviewDto(

    @Schema(title = "Id")
    val id: Long,

    @Schema(title = "Id of the reviewed restroom")
    val restroomId: Long,

    @Schema(title = "Creation datetime")
    val created: ZonedDateTime,

    @Schema(title = "Review marks")
    val marks: MarksDto,

    @Schema(title = "Calculated rating of the review")
    val rating: Double,

    @Schema(title = "Reviewer's comment")
    val comment: String?
)
