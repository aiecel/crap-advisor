package com.aiecel.crapadvisor.config

import com.aiecel.crapadvisor.config.properties.MinioProperties
import io.minio.MinioClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MinioConfig(private val properties: MinioProperties) {

    @Bean
    fun minioClient(): MinioClient =
        MinioClient.builder()
            .endpoint(properties.host)
            .credentials(properties.username, properties.password)
            .build()
}
