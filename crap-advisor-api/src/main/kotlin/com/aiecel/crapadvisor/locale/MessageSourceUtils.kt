package com.aiecel.crapadvisor.locale

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

fun MessageSource.get(messageCode: MessageCode, vararg args: Any) =
    getMessage(messageCode.code, args, LocaleContextHolder.getLocale())
