package com.example.test_8.presentation.mapper

import com.example.test_8.domain.model.HomeItem
import com.example.test_8.presentation.model.HomeImage

fun HomeItem.toHomeImage(): HomeImage {
    return HomeImage(
        id = id,
        cover = cover,
        price = price,
        title = title,
        location = location,
        reactionCount = reactionCount,
        rate = rate
    )
}