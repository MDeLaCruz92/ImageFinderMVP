package com.mdelacruz.imagefindermvp.api.model

import com.google.gson.annotations.SerializedName

data class MetadataResponse(
    @SerializedName("images")
    val metadata: List<Metadata>
)