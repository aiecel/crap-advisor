package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddReviewRequest
import com.aiecel.crapadvisor.mapper.ReviewMapper
import com.aiecel.crapadvisor.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Validated
@RestController
@RequestMapping("/reviews")
@Tag(name = "Reviews")
class ReviewController(
    private val service: ReviewService,
    private val mapper: ReviewMapper
) {

    @GetMapping
    @Operation(summary = "Get all reviews for specified restroom")
    fun getAllByRestroomId(@RequestParam @NotNull restroomId: Long?) =
        service.getAllByRestroomId(restroomId!!).map { mapper.map(it) }

    @PostMapping
    @Operation(summary = "Save a new review")
    fun save(@RequestBody @Validated request: AddReviewRequest) =
        mapper.map(
            service.save(
                restroomId = request.restroomId!!,
                marks = mapper.map(request.marks!!),
                comment = request.comment
            )
        )
}