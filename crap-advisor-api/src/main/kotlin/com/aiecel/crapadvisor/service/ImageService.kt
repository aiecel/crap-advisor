package com.aiecel.crapadvisor.service

import com.aiecel.crapadvisor.config.properties.ImageProperties
import com.aiecel.crapadvisor.model.dto.ImageToSave
import com.aiecel.crapadvisor.model.dto.MinioFile
import com.aiecel.crapadvisor.model.entity.Image
import com.aiecel.crapadvisor.repository.ImageRepository
import mu.KotlinLogging
import net.coobird.thumbnailator.Thumbnails
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService

@Service
class ImageService(
    private val minioService: MinioService,
    private val executorService: ExecutorService,
    private val imageRepository: ImageRepository,
    private val imageProperties: ImageProperties,
) {

    private val log = KotlinLogging.logger { }

    fun saveImages(images: List<ImageToSave>): List<Image> {
        return images
            .map { CompletableFuture.supplyAsync({ saveImage(it) }, executorService) }
            .map { it.join() }
    }

    fun saveImage(image: ImageToSave): Image {
        val originalPath = "${image.name}.${imageProperties.format}"
        val thumbnailPath = "${image.name}-thumbnail.${imageProperties.format}"

        // save original
        val originalImageFile = CompletableFuture.supplyAsync({
            saveImageFile(
                originalPath,
                compressImage(image.file, imageProperties.original.scale, imageProperties.original.quality),
            )
        }, executorService)

        // save thumbnail
        val thumbnailImageFile = CompletableFuture.supplyAsync({
            saveImageFile(
                thumbnailPath,
                compressImage(image.file, imageProperties.thumbnail.scale, imageProperties.thumbnail.quality)
            )
        }, executorService)

        val savedImage = imageRepository.save(
            Image(
                name = image.name,
                originalPath = originalImageFile.join().fullPath,
                thumbnailPath = thumbnailImageFile.join().fullPath
            )
        )

        log.info("Saved image ${image.name} as $originalPath, thumbnail $thumbnailPath")
        return savedImage
    }

    private fun saveImageFile(path: String, content: ByteArray): MinioFile {
        return minioService.saveFile(
            name = path,
            file = content,
            bucket = imageProperties.bucket,
            contentType = imageProperties.contentType
        )
    }

    private fun compressImage(file: MultipartFile, scale: Double, quality: Double): ByteArray {
        val outputStream = ByteArrayOutputStream()

        Thumbnails.of(file.inputStream)
            .scale(scale)
            .outputQuality(quality)
            .outputFormat(imageProperties.format)
            .toOutputStream(outputStream)

        return outputStream.toByteArray()
    }
}
