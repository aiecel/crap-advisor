package com.aiecel.crapadvisor.model

import javax.persistence.Embeddable
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Embeddable
class Position(

    @field:NotNull
    @field:Min(-90)
    @field:Max(90)
    var latitude: Double = 0.0,

    @field:NotNull
    @field:Min(-180)
    @field:Max(180)
    var longitude: Double = 0.0
)