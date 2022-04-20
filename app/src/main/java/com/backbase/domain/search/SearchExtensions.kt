package com.backbase.domain.search

import com.backbase.domain.model.CityDomainModel

/**
 * Custom filter brings together binary search and linear matcher
 * binary search for find first matching element in list
 * linear matcher for add all matched items to result list
 **/
fun List<CityDomainModel>.customFilter(query: String): List<CityDomainModel> {
    val result: ArrayList<CityDomainModel> = ArrayList()
    var start = findMatchedItem(this, query.lowercase(), 0, this.lastIndex, this.lastIndex.ushr(1))
    while (start >= 0 && this.lastIndex >= start) {
        if (this[start].name.startsWith(query, true)) {
            result.add(this[start])
        } else break
        start++
    }

    return result
}

private fun findMatchedItem(
    list: List<CityDomainModel>,
    query: String,
    left: Int,
    right: Int,
    oldMiddle: Int
): Int {
    if (right < left) {
        return oldMiddle
    }

    val middle = (left + right).ushr(1)

    val comp = compareItem(list, query, list[middle], middle)

    if (comp < 0 && (middle - 1) >= 0) {
        return findMatchedItem(list, query, left, middle - 1, middle)
    }

    return if (comp > 0) {
        findMatchedItem(list, query, middle + 1, right, middle)
    } else {
        middle
    }
}

private fun compareItem(
    list: List<CityDomainModel>,
    query: String,
    city: CityDomainModel,
    index: Int
): Int {
    val currentMatch = city.name.startsWith(query, ignoreCase = true)
    val previousMatch = if (index == 0) {
        false
    } else {
        list[index - 1].name.startsWith(query, ignoreCase = true)
    }

    return if (currentMatch) {
        if (previousMatch.not()) {
            0
        } else {
            -1
        }
    } else {
        query.compareTo(city.name, true)
    }
}
