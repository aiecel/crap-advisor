package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.MarksDto
import com.aiecel.crapadvisor.model.Marks
import org.mapstruct.Mapper

@Mapper
interface MarksMapper {
    fun map(marksDto: MarksDto): Marks
}