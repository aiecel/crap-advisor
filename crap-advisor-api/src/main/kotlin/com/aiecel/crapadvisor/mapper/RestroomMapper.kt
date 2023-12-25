package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.RestroomDto
import com.aiecel.crapadvisor.model.entity.Restroom
import com.aiecel.crapadvisor.util.round
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(uses = [LocationMapper::class])
interface RestroomMapper {

    @Mapping(target = "cityId", source = "city.id")
    @Mapping(target = "rating", qualifiedByName = ["round"])
    fun map(restroom: Restroom): RestroomDto

    companion object {

        @JvmStatic
        @Named(value = "round")
        fun map(double: Double) = double.round()
    }
}
