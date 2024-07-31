package com.macek.scratchcard.repository.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActivationResponse(
    val android: Int,
)
