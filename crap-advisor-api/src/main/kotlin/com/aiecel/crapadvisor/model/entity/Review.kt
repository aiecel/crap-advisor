package com.aiecel.crapadvisor.model.entity

import com.aiecel.crapadvisor.model.Marks
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "reviews")
class Review(

    @ManyToOne
    @JoinColumn(name = "restroomId")
    val restroom: Restroom,

    @Embedded
    var marks: Marks,

    @Column(nullable = false)
    var rating: Double,

    @Column(length = 1000)
    var comment: String? = null,

    @OneToMany
    @JoinTable(
        name = "review_images",
        joinColumns = [JoinColumn(name = "review_id")],
        inverseJoinColumns = [JoinColumn(name = "image_id")]
    )
    var images: MutableList<Image> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "review_tags",
        joinColumns = [JoinColumn(name = "review_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    var tags: MutableList<Tag> = mutableListOf()

) : AbstractEntity()
