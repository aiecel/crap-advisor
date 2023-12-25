package com.aiecel.crapadvisor.config.properties

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Range
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "images")
data class ImageProperties(

    @field:NotBlank
    val bucket: String = "public-images",

    @field:NotBlank
    val format: String = "jpg",

    @field:NotBlank
    val contentType: String = "image/jpeg",

    val thumbnail: ImageQualityProperties = ImageQualityProperties(),

    val original: ImageQualityProperties = ImageQualityProperties()
) {

    data class ImageQualityProperties(

        @Range(min = 0, max = 1)
        val scale: Double = 0.5,

        @Range(min = 0, max = 1)
        val quality: Double = 0.5
    )
}
