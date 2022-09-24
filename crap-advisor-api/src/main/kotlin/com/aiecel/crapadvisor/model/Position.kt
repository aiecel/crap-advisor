package com.aiecel.crapadvisor.model

import java.math.BigDecimal
import javax.persistence.Embeddable
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Embeddable
class Position(

    @field:NotNull
    @field:Min(-90)
    @field:Max(90)
    var latitude: BigDecimal = BigDecimal.ZERO,

    @field:NotNull
    @field:Min(-180)
    @field:Max(180)
    var longitude: BigDecimal = BigDecimal.ZERO
)