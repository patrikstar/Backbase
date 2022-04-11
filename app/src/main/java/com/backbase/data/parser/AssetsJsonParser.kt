package com.backbase.data.parser

import com.backbase.data.model.CityDataModel

interface AssetsJsonParser {

    fun parseJson(fileName: String): List<CityDataModel>
}
