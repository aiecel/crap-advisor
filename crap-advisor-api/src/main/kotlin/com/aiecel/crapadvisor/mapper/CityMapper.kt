package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.CityDto
import com.aiecel.crapadvisor.model.entity.City
import com.aiecel.crapadvisor.model.entity.Restroom
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(uses = [LocationMapper::class])
interface CityMapper {

    @Mapping(target = "restrooms", qualifiedByName = ["size"])
    fun map(city: City): CityDto

    companion object {

        @JvmStatic
        @Named(value = "size")
        fun map(restrooms: Collection<Restroom>) = restrooms.size
    }
}
