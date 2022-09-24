package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.model.ReviewMarks
import com.sun.istack.NotNull
import org.hibernate.validator.constraints.Length
import javax.validation.Valid

data class AddReviewRequest(

    @field:NotNull
    val restroomId: Long?,

    @field:NotNull
    @field:Valid
    val marks: ReviewMarks?,

    @field:Length(max = 1000)
    var comment: String?
)
