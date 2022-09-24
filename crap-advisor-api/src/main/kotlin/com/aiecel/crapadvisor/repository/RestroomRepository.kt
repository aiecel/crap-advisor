package com.aiecel.crapadvisor.repository

import com.aiecel.crapadvisor.model.entity.Restroom
import org.springframework.data.jpa.repository.JpaRepository

interface RestroomRepository: JpaRepository<Restroom, Long>