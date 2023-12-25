package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.mapper.CityMapper
import com.aiecel.crapadvisor.service.CityService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/cities")
@Tag(name = "Cities")
class CityController(
    private val cityService: CityService,
    private val cityMapper: CityMapper
) {

    @GetMapping
    @Operation(summary = "Get all cities")
    fun getAll() = cityService.getAll().map { cityMapper.map(it) }
}
