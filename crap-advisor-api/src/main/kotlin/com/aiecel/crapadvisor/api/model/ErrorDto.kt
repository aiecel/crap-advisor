package com.aiecel.crapadvisor.api.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Error")
data class ErrorDto(

    @Schema(title = "Error messages")
    val errorMessages: List<String>
)