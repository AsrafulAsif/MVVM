package com.example.mvvm

import com.google.gson.Gson

object CommonConstants {
    const val CONNECTION_TIMEOUT = 2 * 60 * 1000L
    const val AUTHORIZATION_KEY = "Authorization"
    val DEFAULT_NON_NULL_GSON = Gson()
}