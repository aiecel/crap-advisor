package com.aiecel.crapadvisor.repository

import com.aiecel.crapadvisor.model.Location
import com.aiecel.crapadvisor.model.entity.City
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<City, Long> {
    fun findByLocation(location: Location): City?
}
