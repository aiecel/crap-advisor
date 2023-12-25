package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Location
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption

@Entity
@Table(name = "cities")
class City(

    @Column(length = 100, nullable = false)
    val name: String,

    @Embedded
    var location: Location

) : AbstractEntity() {

    @OneToMany(mappedBy = "city")
    @LazyCollection(value = LazyCollectionOption.EXTRA) // for counting without loading entities
    var restrooms: MutableList<Restroom> = mutableListOf()
}
