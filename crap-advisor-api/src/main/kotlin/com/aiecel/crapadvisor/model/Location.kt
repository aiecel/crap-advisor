package com.aiecel.crapadvisor.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Location(

    @Column(nullable = false)
    var latitude: Double = 0.0,

    @Column(nullable = false)
    var longitude: Double = 0.0
)