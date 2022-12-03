package com.aiecel.crapadvisor.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Suppress("unused")
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [MarkValidator::class])
annotation class Mark(
    val message: String = "{validation.mark}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
