package com.aiecel.crapadvisor.mapper

import com.aiecel.crapadvisor.api.model.TagDto
import com.aiecel.crapadvisor.model.entity.Tag
import org.mapstruct.Mapper

@Mapper(uses = [ImageMapper::class])
interface TagMapper {
    fun map(tag: Tag): TagDto
}
