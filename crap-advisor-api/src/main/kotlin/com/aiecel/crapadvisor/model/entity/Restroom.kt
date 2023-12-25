package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Location
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "restrooms")
class Restroom(

    @Column(length = 50, nullable = false)
    var name: String,

    @Embedded
    var location: Location,

    @ManyToOne
    @JoinColumn(name = "city_id")
    var city: City? = null

) : AbstractEntity() {

    var rating: Double? = null

    @OneToMany(mappedBy = "restroom")
    var reviews: MutableList<Review> = mutableListOf()
}
