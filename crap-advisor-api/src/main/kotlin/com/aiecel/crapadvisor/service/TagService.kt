package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.model.entity.Tag
import com.aiecel.crapadvisor.repository.TagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TagService(
    private val tagRepository: TagRepository,
) {

    fun getAllByName(name: String) =
        tagRepository.findAllByNameContainsIgnoreCase(name)

    @Transactional
    fun getOrCreate(name: String) =
        tagRepository.findByName(name) ?: tagRepository.save(Tag(name))
}
