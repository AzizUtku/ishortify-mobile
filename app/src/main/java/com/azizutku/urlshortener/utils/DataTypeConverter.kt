package com.azizutku.urlshortener.utils

import androidx.room.TypeConverter
import com.azizutku.urlshortener.data.model.Shot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataTypeConverter {
    private val gson = Gson()
    @JvmStatic
    @TypeConverter
    fun stringToList(data: String?): List<Shot> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Shot?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @JvmStatic
    @TypeConverter
    fun listToString(data: List<Shot?>?): String {
        return gson.toJson(data)
    }
}