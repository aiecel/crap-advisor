package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Schema(title = "Add Review Request")
data class AddReviewRequest(

    @field:NotNull
    @Schema(title = "Id of the reviewing restroom", required = true)
    val restroomId: Long?,

    @field:NotNull
    @field:Valid
    @Schema(title = "Review marks", required = true)
    val marks: MarksDto?,

    @field:Length(max = 1000)
    @Schema(title = "Comment")
    var comment: String?
)
