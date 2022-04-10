package com.backbase.data.parser

import android.content.Context
import com.backbase.data.model.CityModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import timber.log.Timber
import java.lang.Exception

@OptIn(ExperimentalSerializationApi::class)
class JsonParserImpl(
    private val context: Context,
    private val json: Json,
) : JsonParser {

    override fun parseJson(): List<CityModel> =
        try {
            context.assets.open("cities.json").use { inputStream ->
                json.decodeFromStream(inputStream)
            }
        } catch (e: Exception) {
            Timber.e("Error while parsinj")
            emptyList()
        }
}
