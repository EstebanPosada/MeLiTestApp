package com.estebanposada.testapp.usecase

import com.estebanposada.testapp.data.source.SearchRepository
import javax.inject.Inject

class FindItemByIdUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend fun execute(id: String) = repository.findById(id)
}