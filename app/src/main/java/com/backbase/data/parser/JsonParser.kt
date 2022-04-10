package com.backbase.data.parser

import com.backbase.data.model.CityModel

interface JsonParser {

    fun parseJson(): List<CityModel>
}
