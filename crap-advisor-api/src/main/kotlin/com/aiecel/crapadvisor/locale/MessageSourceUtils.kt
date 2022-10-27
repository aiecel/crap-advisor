package com.aiecel.crapadvisor.locale

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

object MessageSourceUtils {
    fun MessageSource.get(messageCode: MessageCode, vararg args: Any) =
        this.getMessage(messageCode.code, args, LocaleContextHolder.getLocale())
}