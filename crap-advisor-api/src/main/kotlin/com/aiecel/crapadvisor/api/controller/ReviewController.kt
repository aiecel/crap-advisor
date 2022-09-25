package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddReviewRequest
import com.aiecel.crapadvisor.mapper.ReviewMapper
import com.aiecel.crapadvisor.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/review")
@Tag(name = "Reviews")
class ReviewController(
    private val service: ReviewService,
    private val mapper: ReviewMapper
) {

    @GetMapping("/restroom/{restroomId}")
    @Operation(summary = "Get all reviews for specified restroom")
    fun getAllByRestroomId(@PathVariable("restroomId") restroomId: Long) =
        service.getByRestroomId(restroomId).map { mapper.map(it) }

    @PostMapping
    @Operation(summary = "Save a new review")
    fun save(@RequestBody @Validated request: AddReviewRequest) =
        mapper.map(service.save(request))
}