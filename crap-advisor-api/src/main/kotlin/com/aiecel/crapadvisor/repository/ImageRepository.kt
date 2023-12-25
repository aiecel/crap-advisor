package com.aiecel.crapadvisor.repository

import com.aiecel.crapadvisor.model.entity.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long>
