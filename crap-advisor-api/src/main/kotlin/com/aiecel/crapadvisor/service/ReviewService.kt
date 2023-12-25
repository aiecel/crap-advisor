package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.exception.NotFoundException
import com.aiecel.crapadvisor.model.Marks
import com.aiecel.crapadvisor.model.dto.ImageToSave
import com.aiecel.crapadvisor.model.entity.Review
import com.aiecel.crapadvisor.repository.RestroomRepository
import com.aiecel.crapadvisor.repository.ReviewRepository
import com.aiecel.crapadvisor.util.round
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.Collections.emptyList

@Service
class ReviewService(
    private val restroomService: RestroomService,
    private val imageService: ImageService,
    private val tagService: TagService,
    private val restroomRepository: RestroomRepository,
    private val reviewRepository: ReviewRepository,
) {

    private val log = KotlinLogging.logger { }

    fun getAllByRestroomId(restroomId: Long): List<Review> {
        val reviews = reviewRepository.findAllByRestroomIdOrderByCreatedDesc(restroomId)
        log.debug("Fetched ${reviews.size} review(s) by restroom id $restroomId")
        return reviews
    }

    @Transactional
    fun addNewReview(
        restroomId: Long,
        marks: Marks,
        comment: String? = null,
        imagesFiles: List<MultipartFile> = emptyList(),
        tagNames: List<String> = emptyList(),
    ): Review {
        val restroom = restroomRepository.findById(restroomId)
            .orElseThrow { NotFoundException("Restroom with id $restroomId not found") }

        val images = imageService.saveImages(
            imagesFiles.mapIndexed { i, file ->
                ImageToSave("review-images/${i + 1}", file)
            }
        ).toMutableList()

        val tags = tagNames.map { tagService.getOrCreate(it) }.toMutableList()

        val savedReview = reviewRepository.save(
            Review(
                restroom = restroom,
                marks = marks,
                rating = calculateRating(marks),
                comment = comment?.trim()?.ifBlank { null },
                images = images,
                tags = tags
            )
        )

        restroomService.updateRestroomRating(restroomId)

        log.info(
            "Added new review with id ${savedReview.id} " +
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
}
