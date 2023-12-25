package com.aiecel.crapadvisor.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Configuration
@ConfigurationProperties(prefix = "executor")
class ExecutorConfig(private val poolSize: Int = 4) {

    @Bean
    fun executorService(): ExecutorService = Executors.newWorkStealingPool(poolSize)
}
