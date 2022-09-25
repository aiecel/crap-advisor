package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.api.model.AddReviewRequest
import com.aiecel.crapadvisor.exception.NotFoundException
import com.aiecel.crapadvisor.model.ReviewMarks
import com.aiecel.crapadvisor.model.entity.Restroom
import com.aiecel.crapadvisor.model.entity.Review
import com.aiecel.crapadvisor.repository.RestroomRepository
import com.aiecel.crapadvisor.repository.ReviewRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import kotlin.math.roundToInt

@Service
class ReviewService(
    private val restroomRepository: RestroomRepository,
    private val reviewRepository: ReviewRepository
) {

    private val log = KotlinLogging.logger { }

    fun getByRestroomId(restroomId: Long) = reviewRepository.findAllByRestroomId(restroomId)

    @Transactional
    fun save(request: AddReviewRequest): Review {
        requireNotNull(request.restroomId) { "Restroom id should be specified" }
        requireNotNull(request.marks) { "Marks should be present in a review" }

        val restroom = restroomRepository.findById(request.restroomId)
            .orElseThrow { NotFoundException("Restroom with id ${request.restroomId} not found") }

        val savedReview = reviewRepository.save(
            Review(
                restroom,
                request.marks,
                calculateRating(request.marks),
                request.comment?.ifBlank { null }
            )
        )
        log.info("Saved new review with id ${savedReview.id} for restroom with id ${restroom.id}")
        recalculateRestroomRating(restroom)
        return savedReview
    }

    private fun recalculateRestroomRating(restroom: Restroom) {
        restroom.rating = round(reviewRepository.getAverageReviewRatingByRestroomId(restroom.id))
        restroomRepository.save(restroom)
        log.info("Saved new rating (${restroom.rating}) for restroom with id ${restroom.id}")
    }

    private fun calculateRating(reviewMarks: ReviewMarks): Double {
        val rating = ((reviewMarks.primaryFixtures ?: 3) * 4.0 +
                (reviewMarks.secondaryFixtures ?: 3) * 3.0 +
                (reviewMarks.cleanness ?: 3) * 2.0 +
                (reviewMarks.comfort ?: 3) * 1.0) / 10
        return round(rating)
    }

    private fun round(double: Double) = (double * 100).roundToInt() / 100.0
}