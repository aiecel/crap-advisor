package com.aiecel.crapadvisor.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "images")
class Image(

    @Column(length = 50)
    val name: String? = null,

    @Column(nullable = false)
    val originalPath: String,

    @Column(nullable = false)
    val thumbnailPath: String

) : AbstractEntity()
