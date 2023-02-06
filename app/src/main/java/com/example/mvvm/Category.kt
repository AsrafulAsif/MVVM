package com.example.mvvm

data class Category(
    val deleted: Boolean,
    val description: String,
    val id: String,
    val image: String,
    val keyword: String,
    val level: Int,
    val parentId: Any,
    val position: Int,
    val slug: String,
    val status: Any,
    val title: String,
    val type: String,
    val webImage: String
)