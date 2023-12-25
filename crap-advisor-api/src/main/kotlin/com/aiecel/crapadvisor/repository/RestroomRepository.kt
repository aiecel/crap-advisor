package com.aiecel.crapadvisor.repository

import com.aiecel.crapadvisor.model.entity.Restroom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface RestroomRepository : JpaRepository<Restroom, Long> {

    @Query(
        """
            select r
            from Restroom r
            where r.location.latitude between :fromLatitude and :toLatitude
            and r.location.longitude between :fromLongitude and :toLongitude
        """
    )
    fun findAllInRegion(
        @Param("fromLatitude") fromLatitude: Double,
        @Param("fromLongitude") fromLongitude: Double,
        @Param("toLatitude") toLatitude: Double,
        @Param("toLongitude") toLongitude: Double,
    ): List<Restroom>

    @Modifying
    @Query(
        """
            update Restroom restroom
            set restroom.rating = (
                select avg(review.rating) 
                from Review review 
                where review.restroom = restroom
            )
        """
    )
    fun updateRestroomRating(@Param("id") id: Long): Long
}
