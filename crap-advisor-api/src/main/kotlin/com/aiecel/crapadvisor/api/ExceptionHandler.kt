package com.aiecel.crapadvisor.api

import com.aiecel.crapadvisor.api.model.ErrorDto
import com.aiecel.crapadvisor.exception.NotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MultipartException

@RestControllerAdvice
class ExceptionHandler {

    private val log = KotlinLogging.logger { }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationException(ex: MethodArgumentNotValidException): ErrorDto {
        val errorMessages = ex.bindingResult.fieldErrors
            .map { "${it.field}: ${it.defaultMessage}" }
        return ErrorDto(errorMessages)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MultipartException::class)
    fun multipartException(ex: MultipartException): ErrorDto {
        return ErrorDto(listOf(ex.message!!))
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(ex: NotFoundException): ErrorDto {
        return ErrorDto(listOf(ex.message!!))
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable::class)
    fun exception(ex: Throwable): ErrorDto {
        log.error("Internal server error: ${ex.message}", ex)
        return ErrorDto(listOf(ex.message!!))
    }
}
