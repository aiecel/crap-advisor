package com.aiecel.crapadvisor.config.properties

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties(prefix = "yandex")
data class YandexProperties(

    @field:NotBlank
    val apiKey: String
)
