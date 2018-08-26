package com.mdelacruz.imagefindermvp.api.model

data class ImageResponse(
    val resultCount: Int,
    val images: List<ImageResult>
)