package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.ImageDto
import com.aiecel.crapadvisor.model.entity.Image
import org.mapstruct.Mapper

@Mapper
interface ImageMapper {
    fun map(image: Image): ImageDto
}
