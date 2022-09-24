package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddReviewRequest
import com.aiecel.crapadvisor.mapper.ReviewMapper
import com.aiecel.crapadvisor.service.ReviewService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewService: ReviewService,
    private val mapper: ReviewMapper
) {

    @PostMapping
    fun save(@RequestBody @Validated request: AddReviewRequest) = mapper.map(reviewService.save(request))
}