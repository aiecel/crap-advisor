package com.aiecel.crapadvisor.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [MarkValidator::class])
annotation class Mark(
    val message: String = "оценка должна быть от 1 до 5!",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
