package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.api.model.AddReviewRequest
import com.aiecel.crapadvisor.exception.NotFoundException
import com.aiecel.crapadvisor.model.Marks
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

    fun getByRestroomId(restroomId: Long): List<Review> {
        val reviews = reviewRepository.findAllByRestroomIdOrderByCreatedDesc(restroomId)
        log.debug("Fetched ${reviews.size} review(s) by restroom id $restroomId")
        return reviews
    }

    @Transactional
    fun save(request: AddReviewRequest): Review {
        requireNotNull(request.restroomId) { "Restroom id should be specified" }
        requireNotNull(request.marks) { "Marks should be present in a review" }

        val restroom = restroomRepository.findById(request.restroomId)
            .orElseThrow { NotFoundException("Restroom with id ${request.restroomId} not found") }

        val savedReview = reviewRepository.save(
            Review(
                restroom = restroom,
                marks = request.marks,
                rating = calculateRating(request.marks),
                comment = request.comment?.ifBlank { null }
            )
        )
        log.info("Saved new review with id ${savedReview.id} for restroom with id ${restroom.id}")

        restroom.rating = round(reviewRepository.getAverageReviewRatingByRestroomId(restroom.id))
        restroomRepository.save(restroom)
        log.info("Saved new rating (${restroom.rating}) for restroom with id ${restroom.id}")

        return savedReview
    }

    private fun calculateRating(marks: Marks): Double {
        val rating = ((marks.primaryFixtures ?: 3) * 4.0 +
                (marks.secondaryFixtures ?: 3) * 3.0 +
                (marks.cleanness ?: 3) * 2.0 +
                (marks.comfort ?: 3) * 1.0) / 10
        return round(rating)
    }

    private fun round(double: Double) = (double * 10).roundToInt() / 10.0
}