package com.aiecel.crapadvisor.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class MarkValidator : ConstraintValidator<Mark, Int> {

    override fun isValid(value: Int?, context: ConstraintValidatorContext?) =
        value == null || (value in 1..5)
}