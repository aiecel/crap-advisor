package com.aiecel.crapadvisor.model.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1

    @CreationTimestamp
    @Column(nullable = false)
    val created: ZonedDateTime = ZonedDateTime.now()
}