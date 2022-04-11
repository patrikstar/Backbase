package com.backbase.data.repository

import com.backbase.common.getCitiDataModelList
import com.backbase.data.mapper.CityDataToDomainMapper
import com.backbase.data.parser.AssetsJsonParser
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class DataRepositoryImplTest {

    private val assetsJsonParser: AssetsJsonParser = mockk(relaxed = true)
    private val mapper = CityDataToDomainMapper()

    private val repository = DataRepositoryImpl(assetsJsonParser, mapper)

    @Test
    fun `should return true when call parseAndSaveData and parsed list is not empty`() = runTest {
        // given
        every { assetsJsonParser.parseJson(any()) } returns getCitiDataModelList()

        // when
        val result = repository.parseAndSaveData()

        // then
        assertEquals(true, result)
    }

    @Test
    fun `should return false when call parseAndSaveData and parsed list is empty`() = runTest {
        // given
        every { assetsJsonParser.parseJson(any()) } returns emptyList()

        // when
        val result = repository.parseAndSaveData()

        // then
        assertEquals(false, result)
    }

    @Test
    fun `should return sorted initialList when getInitialList()`() = runTest {
        // given
        every { assetsJsonParser.parseJson(any()) } returns getCitiDataModelList()
        val mappedList = mapper.map(getCitiDataModelList())
        val sortedList = mappedList.sortedWith(
            compareBy(
                { it.nameLowercase },
                { it.country }
            )
        )
        // when
        repository.parseAndSaveData()

        // then
        assertEquals(sortedList, repository.getInitialList())
    }
}
