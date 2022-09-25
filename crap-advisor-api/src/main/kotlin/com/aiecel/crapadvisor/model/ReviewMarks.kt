package com.aiecel.crapadvisor.model

import com.aiecel.crapadvisor.validation.Mark
import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.Embeddable

@Embeddable
@Schema(title = "Review Marks")
class ReviewMarks(

    @field:Mark
    @Schema(title = "Rating of the primary fixtures (toilets etc)", defaultValue = "5")
    var primaryFixtures: Int?,

    @field:Mark
    @Schema(title = "Rating of the primary fixtures (washbasins etc)", defaultValue = "5")
    var secondaryFixtures: Int?,

    @field:Mark
    @Schema(title = "Rating of the restroom's overall cleanness", defaultValue = "5")
    var cleanness: Int?,

    @field:Mark
    @Schema(title = "Rating of the restroom's overall comfort", defaultValue = "5")
    var comfort: Int?
)