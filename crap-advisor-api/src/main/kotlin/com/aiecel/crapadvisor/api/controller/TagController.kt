package com.aiecel.crapadvisor.api.controller

import com.aiecel.crapadvisor.mapper.TagMapper
import com.aiecel.crapadvisor.service.TagService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tags")
@Tag(name = "Tags")
class TagController(
    private val tagService: TagService,
    private val tagMapper: TagMapper,
) {

    @GetMapping
    @Operation(summary = "Get all tags by name")
    fun getAllByName(@RequestParam name: String) =
        tagService.getAllByName(name).map { tagMapper.map(it) }
}
