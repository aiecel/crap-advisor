package com.aiecel.crapadvisor.config.properties

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties(prefix = "minio")
data class MinioProperties(

    @field:NotBlank
    val host: String,

    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String,

    @field:Min(10_000_000)
    val filePartSize: Long = 10_000_000
)
