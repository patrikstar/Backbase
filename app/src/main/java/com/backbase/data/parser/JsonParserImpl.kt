package com.backbase.data.parser

import android.content.Context
import com.backbase.data.model.CityDataModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import timber.log.Timber
import java.lang.Exception

private const val JSON_FILE_NAME = "cities.json"

@OptIn(ExperimentalSerializationApi::class)
class JsonParserImpl(
    private val context: Context,
    private val json: Json,
) : JsonParser {

    override fun parseJson(): List<CityDataModel> =
        try {
            context.assets.open(JSON_FILE_NAME).use { inputStream ->
                json.decodeFromStream(inputStream)
            }
        } catch (e: Exception) {
            Timber.e("Error while parsinj")
            emptyList()
        }
}
