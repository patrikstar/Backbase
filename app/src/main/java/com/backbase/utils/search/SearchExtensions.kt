package com.backbase.utils.search

import com.backbase.domain.model.CityDomainModel

suspend fun List<CityDomainModel>.customFilter(query: String): List<CityDomainModel> {
    val result: ArrayList<CityDomainModel> = ArrayList()
    var start = customFilterImpl(this, query.lowercase(), 0, this.lastIndex, this.lastIndex.ushr(1))

    while (start >= 0 && this.lastIndex >= start) {
        if (this[start].name.startsWith(query, true)) {
            result.add(this[start])
        }
        start++
    }

    return result
}

suspend fun customFilterImpl(
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

    val comp = query.compareTo(list[middle].name, true)

    if (comp < 0 && (middle - 1) >= 0) {
        return customFilterImpl(list, query, left, middle - 1, middle)
    }

    return if (comp > 0) {
        customFilterImpl(list, query, middle + 1, right, middle)
    } else {
        middle
    }
}
