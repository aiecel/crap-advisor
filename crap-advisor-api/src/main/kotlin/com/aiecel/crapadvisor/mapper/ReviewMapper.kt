package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.MarksDto
import com.aiecel.crapadvisor.api.model.ReviewDto
import com.aiecel.crapadvisor.model.Marks
import com.aiecel.crapadvisor.model.entity.Review
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface ReviewMapper {

    @Mapping(target = "restroomId", source = "restroom.id")
    fun map(review: Review): ReviewDto

    fun map(marksDto: MarksDto): Marks
}
