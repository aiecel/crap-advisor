package com.aiecel.crapadvisor.client

import com.aiecel.crapadvisor.client.model.YandexGeocoderResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "yandex-geocode", url = "\${yandex.geocode.host}")
interface YandexGeocoderClient {

    @GetMapping
    fun geocode(
        @RequestParam geocode: String,
        @RequestParam kind: String?,
        @RequestParam("apikey") apiKey: String,
        @RequestParam format: String = "json",
    ): YandexGeocoderResponse
}
