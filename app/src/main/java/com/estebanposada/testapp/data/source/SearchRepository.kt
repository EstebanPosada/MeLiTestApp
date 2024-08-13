package com.estebanposada.testapp.data.source

import com.estebanposada.testapp.domain.Item

interface SearchRepository {
    suspend fun search(query: String): List<Item>
}