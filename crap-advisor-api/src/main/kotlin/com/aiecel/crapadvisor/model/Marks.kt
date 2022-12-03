package com.aiecel.crapadvisor.model

import jakarta.persistence.Embeddable

@Embeddable
class Marks(
    var primaryFixtures: Int? = null,
    var secondaryFixtures: Int? = null,
    var cleanness: Int? = null,
    var comfort: Int? = null
)