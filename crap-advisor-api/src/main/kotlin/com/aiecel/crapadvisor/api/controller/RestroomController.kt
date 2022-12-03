package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddRestroomRequest
import com.aiecel.crapadvisor.mapper.RestroomMapper
import com.aiecel.crapadvisor.service.RestroomService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restrooms")
@Tag(name = "Restrooms")
class RestroomController(
    private val service: RestroomService,
    private val mapper: RestroomMapper
) {

    @GetMapping
    @Operation(summary = "Get all restrooms")
    fun getAll() = service.getAll().map { mapper.map(it) }

    @PostMapping
    @Operation(summary = "Save a new restroom")
    fun save(@RequestBody @Validated request: AddRestroomRequest) =
        mapper.map(
            service.save(
                name = request.name!!,
                location = mapper.map(request.location!!)
            )
        )
}