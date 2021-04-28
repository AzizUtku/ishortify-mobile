package com.azizutku.urlshortener.data.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.azizutku.urlshortener.utils.DataTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shots_data")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("shots")
    @Expose
    @TypeConverters(DataTypeConverter::class)
    val shots: List<Shot>,

    @SerializedName("user")
    @Expose
    @Embedded
    val user: User
)