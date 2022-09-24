package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.ReviewDto
import com.aiecel.crapadvisor.model.entity.Review
import org.mapstruct.Mapper

@Mapper
interface ReviewMapper {
    fun map(review: Review): ReviewDto
}