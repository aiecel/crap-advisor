package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.LocationDto
import com.aiecel.crapadvisor.model.Location
import org.mapstruct.Mapper

@Mapper
interface LocationMapper {
    fun map(locationDto: LocationDto): Location
}