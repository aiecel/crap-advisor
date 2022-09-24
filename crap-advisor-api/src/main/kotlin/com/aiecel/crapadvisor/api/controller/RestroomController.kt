package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddRestroomRequest
import com.aiecel.crapadvisor.mapper.RestroomMapper
import com.aiecel.crapadvisor.service.RestroomService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restroom")
class RestroomController(
    private val service: RestroomService,
    private val mapper: RestroomMapper
) {

    @PostMapping
    fun save(@RequestBody @Validated request: AddRestroomRequest) = mapper.map(
        service.save(request.name!!, request.position!!)
    )

    @GetMapping("/all")
    fun getAll() = service.getAll().map { mapper.map(it) }
}