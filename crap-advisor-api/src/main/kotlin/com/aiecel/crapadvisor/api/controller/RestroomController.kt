package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddRestroomRequest
import com.aiecel.crapadvisor.api.model.RegionRequest
import com.aiecel.crapadvisor.mapper.LocationMapper
import com.aiecel.crapadvisor.mapper.RegionMapper
import com.aiecel.crapadvisor.mapper.RestroomMapper
import com.aiecel.crapadvisor.service.RestroomService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/restrooms")
@Tag(name = "Restrooms")
class RestroomController(
    private val restroomService: RestroomService,
    private val restroomMapper: RestroomMapper,
    private val locationMapper: LocationMapper,
    private val regionMapper: RegionMapper
) {

    @GetMapping
    @Operation(summary = "Get all restrooms")
    fun getAll() = restroomService.getAll().map { restroomMapper.map(it) }

    @GetMapping("/region")
    @Operation(summary = "Get all restrooms in the region")
    fun getAllInRegion(@ParameterObject @Validated request: RegionRequest) =
        restroomService
            .getAllInRegion(regionMapper.map(request))
            .map { restroomMapper.map(it) }

    @PostMapping
    @Operation(summary = "Add a new restroom")
    fun addNewRestroom(@RequestBody @Validated request: AddRestroomRequest) =
        restroomMapper.map(
            restroomService.addNewRestroom(
                name = request.name!!,
                location = locationMapper.map(request.location!!)
            )
        )
}
