package com.aiecel.crapadvisor.model

import jakarta.persistence.Embeddable

@Embeddable
class Marks(
    var primaryFixtures: Int? = null,
    var secondaryFixtures: Int? = null,
    var cleanness: Int? = 3,
    var comfort: Int? = 3,

//    var isOpen: Boolean = true,
//    var price: Int? = null,
//    var hasSoap: Boolean? = null,
//    var hasWater: Boolean? = null,
//    var smell: Int? = 3,
)
