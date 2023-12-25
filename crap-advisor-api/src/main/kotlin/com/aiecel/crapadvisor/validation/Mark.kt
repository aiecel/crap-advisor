package com.aiecel.crapadvisor.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Suppress("unused")
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [MarkValidator::class])
annotation class Mark(
    val message: String = "Mark should be a value between 1 and 5",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
