package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.model.Location
import com.aiecel.crapadvisor.model.entity.City
import com.aiecel.crapadvisor.repository.CityRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class CityService(
    private val geocodingService: GeocodingService,
    private val cityRepository: CityRepository,
) {

    private val log = KotlinLogging.logger { }

    fun getAll(): List<City> {
        val cities = cityRepository.findAll().toList()
        log.debug("Fetched ${cities.size} cities(s)")
        return cities
    }

    fun getOrGeocodeCity(location: Location): City {
        val geocodedCity = geocodingService.getCityNameByLocation(location)
        val existingCity = cityRepository.findByLocation(geocodedCity.location)

        if (existingCity != null && geocodedCity.location == existingCity.location) {
            log.info("Found existing city ${existingCity.id} for location $location")
            return existingCity
        }

        return addNewCity(geocodedCity.name, geocodedCity.location)
    }

    private fun addNewCity(name: String, location: Location): City {
        val savedCity = cityRepository.save(City(name, location))
        log.info("Added new city with id ${savedCity.id} at location $location")
        return savedCity
    }
}
