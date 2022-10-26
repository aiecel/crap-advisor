package com.aiecel.crapadvisor.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Location(

    @Column(nullable = false)
    var latitude: Double = 0.0,

    @Column(nullable = false)
    var longitude: Double = 0.0
)