package com.aiecel.crapadvisor.model

import javax.persistence.Embeddable

@Embeddable
class Marks(
    var primaryFixtures: Int?,
    var secondaryFixtures: Int?,
    var cleanness: Int?,
    var comfort: Int?
)