package com.azizutku.urlshortener.data.model


import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("surname")
    @Expose
    @ColumnInfo(name = "surname")
    val surname: String
)