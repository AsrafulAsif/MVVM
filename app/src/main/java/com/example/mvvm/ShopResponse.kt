package com.example.mvvm
//new
data class ShopResponse(
    val currentPageNumber: Int,
    val first: Boolean,
    val last: Boolean,
    val message: String,
    val numberOfElements: Int,
    val shops: List<Shop>,
    val statusCode: Int,
    val totalElements: Int,
    val totalPages: Int
)