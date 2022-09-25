package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.api.model.AddRestroomRequest
import com.aiecel.crapadvisor.model.entity.Restroom
import com.aiecel.crapadvisor.repository.RestroomRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class RestroomService(private val restroomRepository: RestroomRepository) {

    private val log = KotlinLogging.logger { }

    fun getAll(): List<Restroom> {
        val restrooms = restroomRepository.findAll().toList()
        log.debug("Fetched ${restrooms.size} restroom(s)")
        return restrooms
    }

    fun save(request: AddRestroomRequest): Restroom {
        requireNotNull(request.location) { "Restroom position should be specified" }
        val savedRestroom = restroomRepository.save(
            Restroom(
                name = request.name?.ifBlank { null },
                location = request.location
            )
        )
        log.info("Saved new restroom with id ${savedRestroom.id}")
        return savedRestroom
    }
}