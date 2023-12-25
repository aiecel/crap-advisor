package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.client.YandexGeocoderClient
import com.aiecel.crapadvisor.config.properties.YandexProperties
import com.aiecel.crapadvisor.exception.GeocoderException
import com.aiecel.crapadvisor.model.Location
import com.aiecel.crapadvisor.model.dto.GeocodedCity
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class GeocodingService(
    private val yandexGeocoderClient: YandexGeocoderClient,
    private val yandexProperties: YandexProperties
) {

    private val log = KotlinLogging.logger { }

    fun getCityNameByLocation(location: Location): GeocodedCity {
        val response = try {
            yandexGeocoderClient.geocode(
                geocode = "${location.longitude},${location.latitude}",
                kind = "locality", //todo
                apiKey = yandexProperties.apiKey,
            ).response
        } catch (e: Throwable) {
            throw GeocoderException(e.message, e)
        }

        val geoObject = response
            .geoObjectCollection
            .featureMembers
            .firstOrNull { featureMember ->
                featureMember
                    .geoObject
                    .metaDataProperty
                    .geocoderMetaData
                    .address
                    .components
                    .count { it.kind == "locality" } == 1 //todo
            }
            ?.geoObject ?: throw GeocoderException("Cannot find any city for location $location")

        val locationComponents = geoObject.point.pos
            .split(" ")
            .map { it.toDouble() }

        log.info("Geocoded location $location as city ${geoObject.name}")

        return GeocodedCity(geoObject.name, Location(locationComponents[1], locationComponents[0]))
    }
}
