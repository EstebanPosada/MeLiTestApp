package com.estebanposada.testapp.app.server

sealed interface ResultHandler<out T> {
    data class Error<out T>(val error: String) : ResultHandler<T>
    data class Success<out T>(val data: T) : ResultHandler<T>
}