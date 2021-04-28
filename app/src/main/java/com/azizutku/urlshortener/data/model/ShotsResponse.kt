package com.azizutku.urlshortener.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ShotsResponse(
    @SerializedName("data")
    @Expose
    val data: List<Data>?,
    @SerializedName("success")
    @Expose
    val success: Boolean?
)