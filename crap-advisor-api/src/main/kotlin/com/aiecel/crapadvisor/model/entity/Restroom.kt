package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Location
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Table

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