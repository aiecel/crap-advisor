package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.exception.NotFoundException
import com.aiecel.crapadvisor.locale.MessageCode
import com.aiecel.crapadvisor.locale.get
import com.aiecel.crapadvisor.model.Marks
import com.aiecel.crapadvisor.model.entity.Review
import com.aiecel.crapadvisor.repository.RestroomRepository
import com.aiecel.crapadvisor.repository.ReviewRepository
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import kotlin.math.roundToInt

@Service
class ReviewService(
    private val restroomRepository: RestroomRepository,
    private val reviewRepository: ReviewRepository,
    private val messageSource: MessageSource
) {

    private val log = KotlinLogging.logger { }

    fun getAllByRestroomId(restroomId: Long): List<Review> {
        val reviews = reviewRepository.findAllByRestroomIdOrderByCreatedDesc(restroomId)
        log.debug("Fetched ${reviews.size} review(s) by restroom id $restroomId")
        return reviews
    }

    @Transactional
    fun save(restroomId: Long, marks: Marks, comment: String? = null): Review {
        val restroom = restroomRepository.findById(restroomId)
            .orElseThrow { NotFoundException(messageSource.get(MessageCode.RESTROOM_NOT_FOUND, restroomId)) }

        val savedReview = reviewRepository.save(
            Review(
                restroom = restroom,
                marks = marks,
                rating = calculateRating(marks),
                comment = comment?.trim()?.ifBlank { null }
            )
        )

        restroom.rating = reviewRepository.getAverageReviewRatingByRestroomId(restroom.id).round()
        restroomRepository.save(restroom)

        log.info(
            "Saved new review with id ${savedReview.id} " +
                    "for restroom with id $restroomId, " +
                    "restroom rating is now ${restroom.rating}"
        )

        return savedReview
    }

    private fun calculateRating(marks: Marks): Double {
        val rating = ((marks.primaryFixtures ?: 3) * 4.0 +
                (marks.secondaryFixtures ?: 3) * 3.0 +
                (marks.cleanness ?: 3) * 2.0 +
                (marks.comfort ?: 3) * 1.0) / 10
        return rating.round()
    }

    private fun Double.round() = (this * 10).roundToInt() / 10.0
}
