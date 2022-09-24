package com.aiecel.crapadvisor.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MarkValidator : ConstraintValidator<Mark, Int> {

    override fun isValid(value: Int?, context: ConstraintValidatorContext?): Boolean {
        return value == null || (value in 1..5)
    }
}