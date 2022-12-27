package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Location
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "restrooms")
class Restroom(

    @Column(nullable = false)
    var name: String,

    @Embedded
    var location: Location = Location()

) : AbstractEntity() {

    var rating: Double? = null
}
