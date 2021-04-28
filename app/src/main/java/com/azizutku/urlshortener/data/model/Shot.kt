package com.azizutku.urlshortener.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Shot(
    @SerializedName("_id")
    @Expose
    val id: String,
    @SerializedName("InOut")
    @Expose
    val inOut: Boolean,
    @SerializedName("point")
    @Expose
    val point: Int,
    @SerializedName("segment")
    @Expose
    val segment: Int,
    @SerializedName("ShotPosX")
    @Expose
    val shotPosX: Double,
    @SerializedName("ShotPosY")
    @Expose
    val shotPosY: Double
)