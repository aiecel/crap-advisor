package com.aiecel.crapadvisor.client.model

import com.fasterxml.jackson.annotation.JsonProperty

data class YandexGeocoderResponse(val response: Response) {

    data class Response(
        @JsonProperty("GeoObjectCollection") val geoObjectCollection: GeoObjectCollection
    )

    data class GeoObjectCollection(
        @JsonProperty("featureMember") val featureMembers: List<FeatureMember> = emptyList()
    )

    data class FeatureMember(
        @JsonProperty("GeoObject") val geoObject: GeoObject
    )

    data class GeoObject(
        val name: String,
        val metaDataProperty: MetaDataProperty,
        @JsonProperty("Point") val point: Point
    )

    data class MetaDataProperty(
        @JsonProperty("GeocoderMetaData") val geocoderMetaData: GeocoderMetaData
    )

    data class GeocoderMetaData(
        @JsonProperty("Address") val address: Address
    )

    data class Address(
        @JsonProperty("Components") val components: List<AddressComponent>
    )

    data class AddressComponent(
        val kind: String,
        val name: String
    )

    data class Point(val pos: String)
}
