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

        val errorMessage = ex.bindingResult.fieldErrors
            .joinToString(", ") { "${it.field} ${it.defaultMessage}" }

        return ErrorDto(errorMessage)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException): ErrorDto {
        return ErrorDto(ex.message!!)
    }
}