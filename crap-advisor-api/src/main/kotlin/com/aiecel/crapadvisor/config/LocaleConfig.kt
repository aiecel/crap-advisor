package com.aiecel.crapadvisor.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

@Configuration
@ConfigurationProperties(prefix = "locale")
class LocaleConfig(var defaultLocale: Locale = Locale.ENGLISH) {

    @Bean
    fun messageSource() =
        ResourceBundleMessageSource().apply {
            setBasename("messages")
            setDefaultEncoding("UTF-8")
            setDefaultLocale(defaultLocale)
            setUseCodeAsDefaultMessage(true)
            setFallbackToSystemLocale(false)
        }

    @Bean
    fun localeResolver() =
        AcceptHeaderLocaleResolver().apply {
            defaultLocale = this@LocaleConfig.defaultLocale
        }
}