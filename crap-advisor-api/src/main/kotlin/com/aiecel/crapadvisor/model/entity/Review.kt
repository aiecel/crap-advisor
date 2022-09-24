package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.ReviewMarks
import javax.persistence.*

@Entity
@Table(name = "reviews")
class Review(

    @ManyToOne
    @JoinColumn(name = "restroomId")
    val restroom: Restroom,

    @Embedded
    val reviewMarks: ReviewMarks,

    val rating: Double?,

    @Column(length = 1000)
    val comment: String?

) : AbstractEntity()