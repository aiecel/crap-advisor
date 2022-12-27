package com.aiecel.crapadvisor.api

import com.aiecel.crapadvisor.api.model.ErrorDto
import com.aiecel.crapadvisor.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationException(ex: MethodArgumentNotValidException): ErrorDto {
        val errorMessages = ex.bindingResult.fieldErrors
            .map { "${it.field}: ${it.defaultMessage}" }
        return ErrorDto(errorMessages)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException): ErrorDto {
        return ErrorDto(listOf(ex.message!!))
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException::class)
    fun runtimeException(ex: RuntimeException): ErrorDto {
        return ErrorDto(listOf(ex.message!!))
    }
}
