package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Image Info")
data class ImageDto(

    @Schema(title = "Path to original version")
    val originalPath: String,

    @Schema(title = "Path to thumbnail version")
    val thumbnailPath: String
)
