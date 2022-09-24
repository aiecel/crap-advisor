package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.model.ReviewMarks
import java.time.ZonedDateTime

data class ReviewDto(
    val id: Long,
    val restroomId: Long,
    val created: ZonedDateTime,
    val reviewMarks: ReviewMarks,
    val rating: Double,
    val comment: String
)