package com.example.gourmet_inventory_mobile.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateSerializer : JsonSerializer<LocalDate> {
    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): com.google.gson.JsonElement? {
        return JsonPrimitive(src?.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }
}

class LocalDateDeserializer : JsonDeserializer<LocalDate> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate {
        return LocalDate.parse(json?.asString, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}
