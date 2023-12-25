package com.aiecel.crapadvisor.model.dto

data class MinioFile(
    val bucket: String,
    val file: String
) {
    val fullPath = "$bucket/$file"
}
