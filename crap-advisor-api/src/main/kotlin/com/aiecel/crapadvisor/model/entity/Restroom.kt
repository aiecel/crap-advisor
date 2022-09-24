package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Position
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "restrooms")
class Restroom(

    var name: String? = null,

    var rating: Double? = null,

    @Embedded
    var position: Position = Position()

) : AbstractEntity()