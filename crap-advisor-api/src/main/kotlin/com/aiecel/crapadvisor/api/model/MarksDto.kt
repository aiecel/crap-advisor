package com.aiecel.crapadvisor.api.model

import com.aiecel.crapadvisor.validation.Mark
import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "Review Marks")
open class MarksDto(

    @field:Mark
    @Schema(title = "Rating of the primary fixtures (toilets etc.)", defaultValue = "5")
    var primaryFixtures: Int? = null,

    @field:Mark
    @Schema(title = "Rating of the secondary fixtures (washbasins etc.)", defaultValue = "5")
    var secondaryFixtures: Int? = null,

    @field:Mark
    @Schema(title = "Rating of the restroom's overall cleanness", defaultValue = "5")
    var cleanness: Int? = null,

    @field:Mark
    @Schema(title = "Rating of the restroom's overall comfort", defaultValue = "5")
    var comfort: Int? = null,
)
