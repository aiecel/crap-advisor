package com.aiecel.crapadvisor.exception

class GeocoderException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)
