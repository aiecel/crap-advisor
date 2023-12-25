package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length
import org.springframework.web.multipart.MultipartFile

@Schema(title = "Add Review Request")
data class AddReviewRequest(

    @field:NotNull
    @Schema(title = "ID of the reviewing restroom", required = true)
    val restroomId: Long?,

    @field:Length(max = 1000)
    @Schema(title = "Comment")
    val comment: String?,

    @field:Size(max = 10)
    @Schema(title = "Images")
    var images: List<MultipartFile>,

    @field:Size(max = 10)
    @Schema(title = "Tag names")
    val tags: List<String>

) : MarksDto() // dizzy hack
