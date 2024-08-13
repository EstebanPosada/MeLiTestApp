package com.estebanposada.testapp.app.server

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/sites/MLA/search")
    suspend fun getItems(
        @Query("q") query: String
    ): Response<RootResponse>
}