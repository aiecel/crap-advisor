package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.RegionRequest
import com.aiecel.crapadvisor.model.dto.Region
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface RegionMapper {

    @Mapping(source = "fromLatitude", target = "fromLocation.latitude")
    @Mapping(source = "fromLongitude", target = "fromLocation.longitude")
    @Mapping(source = "toLatitude", target = "toLocation.latitude")
    @Mapping(source = "toLongitude", target = "toLocation.longitude")
    fun map(request: RegionRequest): Region
}
