package com.backbase.data.mapper

import com.backbase.data.model.CityCoordinates
import com.backbase.data.model.CityDataModel
import com.backbase.domain.model.CityDomainModel
import org.junit.Test
import kotlin.test.assertEquals

class CityDataToDomainMapperTest {

    private val mapper = CityDataToDomainMapper()

    private val initialList = listOf(
        CityDataModel(
            country = "UA",
            name = "Kyiv",
            id = 0,
            coordinates = CityCoordinates(
                lat = 0.0f,
                lon = 0.0f
            )
        )
    )

    private val mappedList = listOf(
        CityDomainModel(
            country = "UA",
            name = "Kyiv",
            id = 0,
            nameLowercase = "kyiv",
            latitude = 0.0f,
            longitude = 0.0f
        )
    )

    @Test
    fun `should map from data to domain city model`() {
        assertEquals(mappedList, mapper.map(initialList))
    }

    @Test
    fun `should return empty list when map empty list`() {
        val emptyInitialList = emptyList<CityDomainModel>()
        assertEquals(emptyInitialList, mapper.map(emptyList()))
    }
}
