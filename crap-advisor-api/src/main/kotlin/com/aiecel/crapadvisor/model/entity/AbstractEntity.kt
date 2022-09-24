package com.aiecel.crapadvisor.model.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1

    @CreationTimestamp
    val created: ZonedDateTime = ZonedDateTime.now()
}