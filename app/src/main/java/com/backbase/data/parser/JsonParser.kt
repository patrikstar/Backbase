package com.backbase.data.parser

import com.backbase.data.model.CityDataModel

interface JsonParser {

    fun parseJson(): List<CityDataModel>
}
