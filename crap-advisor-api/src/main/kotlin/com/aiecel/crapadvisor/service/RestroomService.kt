package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.exception.GeocoderException
import com.aiecel.crapadvisor.exception.NotFoundException
import com.aiecel.crapadvisor.model.Location
import com.aiecel.crapadvisor.model.dto.Region
import com.aiecel.crapadvisor.model.entity.Restroom
import com.aiecel.crapadvisor.repository.RestroomRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RestroomService(
    private val cityService: CityService,
    private val restroomRepository: RestroomRepository
) {

    private val log = KotlinLogging.logger { }

    fun getAll(): List<Restroom> {
        val restrooms = restroomRepository.findAll().toList()
        log.debug("Fetched ${restrooms.size} restroom(s)")
        return restrooms
    }

    fun getAllInRegion(region: Region): List<Restroom> {
        val restrooms = restroomRepository.findAllInRegion(
            region.lowerLeftLocation.latitude,
            region.lowerLeftLocation.longitude,
            region.upperRightLocation.latitude,
            region.upperRightLocation.longitude,
        )
        log.debug("Fetched ${restrooms.size} restroom(s) for region $region")
        return restrooms
    }

    @Transactional
    fun addNewRestroom(name: String, location: Location): Restroom {
        val city = try {
            cityService.getOrGeocodeCity(location)
        } catch (e: GeocoderException) {
            log.error("Cannot get city for location $location: ${e.message}", e)
            null
        }
        val savedRestroom = restroomRepository.save(Restroom(name, location, city))
        log.info("Added new restroom with id ${savedRestroom.id}")
        return savedRestroom
    }

    fun updateRestroomRating(id: Long) {
        val restroomsUpdated = restroomRepository.updateRestroomRating(id)
        if (restroomsUpdated == 0L) {
            throw NotFoundException("Restroom with id $id not found")
        }
    }
}
