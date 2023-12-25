package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.api.model.AddReviewRequest
import com.aiecel.crapadvisor.mapper.ReviewMapper
import com.aiecel.crapadvisor.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotNull
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@RequestMapping("/reviews")
@Tag(name = "Reviews")
class ReviewController(
    private val reviewService: ReviewService,
    private val reviewMapper: ReviewMapper
) {

    @GetMapping
    @Operation(summary = "Get all reviews for specified restroom")
    fun getAllByRestroomId(@RequestParam @NotNull restroomId: Long?) =
        reviewService.getAllByRestroomId(restroomId!!).map { reviewMapper.map(it) }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @Operation(summary = "Add a new review")
    fun addNewReview(@ModelAttribute @Validated request: AddReviewRequest) =
        reviewMapper.map(
            reviewService.addNewReview(
                restroomId = request.restroomId!!,
                marks = reviewMapper.map(request),
                comment = request.comment,
                imagesFiles = request.images
            )
        )
}
