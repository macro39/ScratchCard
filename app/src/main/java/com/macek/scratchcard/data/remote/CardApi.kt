package com.macek.scratchcard.data.remote

import com.macek.scratchcard.data.remote.responses.ActivationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApi {
    @GET("version")
    suspend fun activate(@Query("code") code: String): Response<ActivationResponse>
}