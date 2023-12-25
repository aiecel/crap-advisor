package com.aiecel.crapadvisor

import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.aiecel.crapadvisor.config.properties")
@EnableFeignClients
@ImportAutoConfiguration(FeignAutoConfiguration::class) // Spring Boot 3 hack
class CrapAdvisorApiApplication

fun main(args: Array<String>) {
    runApplication<CrapAdvisorApiApplication>(*args)
}
