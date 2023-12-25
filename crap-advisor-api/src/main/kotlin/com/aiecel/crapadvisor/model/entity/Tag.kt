package com.aiecel.crapadvisor.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tags")
class Tag(

    @Column(length = 20, nullable = false)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "icon_id")
    val icon: Image? = null

) : AbstractEntity()
