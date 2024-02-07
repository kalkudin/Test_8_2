package com.example.test_8.data.remote.mapper

import com.example.test_8.data.remote.dto.HomeItemDto
import com.example.test_8.domain.model.HomeItem

fun HomeItemDto.toHomeItem(): HomeItem {
    return HomeItem(
        id = id,
        cover = cover,
        price = price,
        title = title,
        location = location,
        reactionCount = reactionCount,
        rate = rate ?: 0
    )
}