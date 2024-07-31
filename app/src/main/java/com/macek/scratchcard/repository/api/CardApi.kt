package com.macek.scratchcard.repository.api

import com.macek.scratchcard.repository.api.model.ActivationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApi {
    @GET("version")
    suspend fun activate(@Query("code") code: String): Response<ActivationResponse>
}