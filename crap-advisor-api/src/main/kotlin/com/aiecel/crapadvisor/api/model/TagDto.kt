package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Tag Info")
data class TagDto(

    @Schema(title = "Tag name")
    val name: String,

    @Schema(title = "Tag icon")
    val icon: ImageDto?
)
