package com.aiecel.crapadvisor.repository

import com.aiecel.crapadvisor.model.entity.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ReviewRepository : JpaRepository<Review, Long> {

    @Query("select avg(r.rating) from Review r where r.restroom.id = ?1")
    fun getAverageReviewRatingByRestroomId(restroomId: Long): Double

    fun findAllByRestroomIdOrderByCreatedDesc(restroomId: Long): List<Review>
}