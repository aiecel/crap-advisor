package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Marks
import javax.persistence.*

@Entity
@Table(name = "reviews")
class Review(

    @ManyToOne
    @JoinColumn(name = "restroomId")
    val restroom: Restroom,

    @Embedded
    val marks: Marks,

    val rating: Double?,

    @Column(length = 1000)
    val comment: String?

) : AbstractEntity()