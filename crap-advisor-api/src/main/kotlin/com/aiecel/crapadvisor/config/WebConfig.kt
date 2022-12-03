package com.aiecel.crapadvisor.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val messageSource: MessageSource) : WebMvcConfigurer {

    override fun getValidator() =
        LocalValidatorFactoryBean().apply {
            setValidationMessageSource(messageSource)
        }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }
}
