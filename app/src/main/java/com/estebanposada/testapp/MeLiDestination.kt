package com.estebanposada.testapp

import kotlinx.serialization.Serializable

@Serializable
object Search

@Serializable
data class Result(val query: String)

@Serializable
data class Detail(val id: String)