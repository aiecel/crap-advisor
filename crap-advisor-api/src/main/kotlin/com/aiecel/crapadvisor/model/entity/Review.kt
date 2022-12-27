package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Marks
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

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
