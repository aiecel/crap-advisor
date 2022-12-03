package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.LocationDto
import com.aiecel.crapadvisor.api.model.RestroomDto
import com.aiecel.crapadvisor.model.Location
import com.aiecel.crapadvisor.model.entity.Restroom
import org.mapstruct.Mapper

@Mapper
interface RestroomMapper {
    fun map(restroom: Restroom): RestroomDto
    fun map(location: LocationDto): Location
}