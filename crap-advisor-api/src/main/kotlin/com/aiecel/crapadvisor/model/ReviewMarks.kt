package com.aiecel.crapadvisor.model

import com.aiecel.crapadvisor.validation.Mark
import javax.persistence.Embeddable

@Embeddable
class ReviewMarks(

    @field:Mark
    var primaryFixtures: Int?,

    @field:Mark
    var secondaryFixtures: Int?,

    @field:Mark
    var cleanness: Int?,

    @field:Mark
    var comfort: Int?
)