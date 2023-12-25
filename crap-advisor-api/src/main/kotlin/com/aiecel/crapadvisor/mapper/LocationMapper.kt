package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.LocationDto
import com.aiecel.crapadvisor.model.Location
import org.mapstruct.Mapper

@Mapper
interface LocationMapper {
    fun map(location: Location): LocationDto
    fun map(location: LocationDto): Location
}
