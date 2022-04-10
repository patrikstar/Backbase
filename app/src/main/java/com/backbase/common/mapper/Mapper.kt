package com.backbase.common.mapper

interface Mapper <in M, out E> {

    fun map(data: M): E
}