package com.macek.scratchcard.data.remote.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActivationResponse(
    val android: Int,
)
