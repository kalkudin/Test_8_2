package com.example.test_8.data.remote.dto

import com.squareup.moshi.Json

data class HomeItemDto(
    val id : Int,
    val cover : String,
    val price : String,
    val title : String,
    val location: String,
    @Json(name = "reaction_count")
    val reactionCount : Int,
    val rate : Int?
)