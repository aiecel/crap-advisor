package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.config.properties.MinioProperties
import com.aiecel.crapadvisor.exception.FileServiceException
import com.aiecel.crapadvisor.model.dto.MinioFile
import io.minio.BucketExistsArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class MinioService(
    private val minioClient: MinioClient,
    private val minioProperties: MinioProperties,
) {

    private val log = KotlinLogging.logger { }

    fun saveFile(bucket: String, name: String, file: ByteArray, contentType: String): MinioFile {
        try {
            if (!bucketExists(bucket)) {
                throw FileServiceException("Bucket $bucket does not exist")
            }

            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .`object`(name)
                    .stream(file.inputStream(), file.size.toLong(), minioProperties.filePartSize)
                    .contentType(contentType)
                    .build()
            )

            log.info("Saved file $name in bucket $bucket")

            return MinioFile(bucket, name)
        } catch (e: Throwable) {
            throw FileServiceException("Cannot save file $name: ${e.message}", e)
        }
    }

    private fun bucketExists(bucket: String): Boolean {
        return minioClient.bucketExists(
            BucketExistsArgs.builder()
                .bucket(bucket)
                .build()
        )
    }
}
