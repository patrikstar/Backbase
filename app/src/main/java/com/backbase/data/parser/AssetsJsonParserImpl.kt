package com.backbase.data.parser

import android.content.Context
import com.backbase.data.model.CityDataModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import timber.log.Timber
import java.lang.Exception

@OptIn(ExperimentalSerializationApi::class)
class AssetsJsonParserImpl(
    private val context: Context,
    private val json: Json,
) : AssetsJsonParser {

    override fun parseJson(fileName: String): List<CityDataModel> =
        try {
            context.assets.open(fileName).use { inputStream ->
                json.decodeFromStream(inputStream)
            }
        } catch (e: Exception) {
            Timber.e("Error while parsinj")
            emptyList()
        }
}
