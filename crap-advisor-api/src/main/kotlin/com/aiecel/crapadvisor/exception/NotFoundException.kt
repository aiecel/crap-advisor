package com.aiecel.crapadvisor.exception

class NotFoundException(
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)
