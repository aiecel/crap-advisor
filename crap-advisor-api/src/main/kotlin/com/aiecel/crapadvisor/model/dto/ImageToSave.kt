package com.aiecel.crapadvisor.model.dto

import org.springframework.web.multipart.MultipartFile

data class ImageToSave(val name: String, val file: MultipartFile)
