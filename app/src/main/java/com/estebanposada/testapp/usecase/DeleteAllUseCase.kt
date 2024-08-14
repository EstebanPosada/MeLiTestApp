package com.estebanposada.testapp.usecase

import com.estebanposada.testapp.data.source.SearchRepository
import javax.inject.Inject

class DeleteAllUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend fun execute() = repository.deleteAll()
}