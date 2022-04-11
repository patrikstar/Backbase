package com.backbase.domain.repository

import com.backbase.common.getCityDomainModelList
import com.backbase.domain.DomainRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@ExperimentalCoroutinesApi
class DomainRepositoryImplTest {

    private val dataRepository: DataRepository = mockk(relaxed = true)

    private val repository = DomainRepositoryImpl(dataRepository)

    @Test
    fun `should return false when call parseAndSaveData() and dataRepository returns false`() = runTest {
        // given
        coEvery { dataRepository.parseAndSaveData() } returns false

        // when
        val result = repository.parseAndSaveData()

        // then
        assertEquals(false, result)
    }

    @Test
    fun `should return true when call parseAndSaveData() and dataRepository returns true`() = runTest {
        // given
        coEvery { dataRepository.parseAndSaveData() } returns true

        // when
        val result = repository.parseAndSaveData()

        // then
        assertEquals(true, result)
    }

    @Test
    fun `should post initial list when call fetchData()`() = runTest {
        // given
        coEvery { dataRepository.getInitialList() } returns getCityDomainModelList()

        // when
        repository.fetchData()
        val initialList = dataRepository.getInitialList()
        val result = repository.searchResultFlow().first()

        // then
        assertEquals(initialList, result)
    }

    @Test
    fun `should post initial list when call searchWithKey() with empty key`() = runTest {
        // given
        coEvery { dataRepository.getInitialList() } returns getCityDomainModelList()
        repository.fetchData()
        repository.searchWithKey("")

        // when
        val initialList = dataRepository.getInitialList()
        val result = repository.searchResultFlow().first()

        // then
        assertEquals(initialList, result)
    }

    @Test
    fun `should post filtered list when call searchWithKey() with key K`() = runTest {
        // given
        coEvery { dataRepository.getInitialList() } returns getCityDomainModelList()
        val key = "k"
        repository.fetchData()

        // when
        repository.searchWithKey(key)
        val initialList = dataRepository.getInitialList()
        val filteredList = initialList.filter { it.name.startsWith(key, true) }
        val result = repository.searchResultFlow().first()

        // then
        assertEquals(filteredList, result)
    }

    @Test
    fun `should post empty list when searchWithKey() with key U`() = runTest {
        // given
        coEvery { dataRepository.getInitialList() } returns getCityDomainModelList()
        val key = "U" // there is no items that starts with "U" in initialList
        repository.fetchData()

        // when
        repository.searchWithKey(key)
        val result = repository.searchResultFlow().first()

        // then
        assertEquals(emptyList(), result)
    }

    @Test
    fun `should not post empty list when searchWithKey() found some items`() = runTest {
        // given
        coEvery { dataRepository.getInitialList() } returns getCityDomainModelList()
        val key = "K"
        repository.fetchData()

        // when
        repository.searchWithKey(key)
        val result = repository.searchResultFlow().first()

        // then
        assertFalse(result.isEmpty())
    }
}
