package com.aiecel.crapadvisor.repository

import com.aiecel.crapadvisor.model.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {
    fun findByName(name: String): Tag?
    fun findAllByNameContainsIgnoreCase(name: String): List<Tag>
}
