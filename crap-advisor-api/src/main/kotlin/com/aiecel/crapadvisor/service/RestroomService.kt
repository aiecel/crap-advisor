package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.model.Position
import com.aiecel.crapadvisor.model.entity.Restroom
import com.aiecel.crapadvisor.repository.RestroomRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class RestroomService(private val restroomRepository: RestroomRepository) {

    private val log = KotlinLogging.logger { }

    fun getAll() = restroomRepository.findAll().toList()

    fun save(name: String, position: Position): Restroom {
        val savedRestroom = restroomRepository.save(
            Restroom(
                name = name.ifBlank { null },
                position = position
            )
        )

        log.info("Saved new restroom with id ${savedRestroom.id}")
        return savedRestroom
    }
}