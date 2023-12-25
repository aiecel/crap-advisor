package com.aiecel.crapadvisor.exception

class FileServiceException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)