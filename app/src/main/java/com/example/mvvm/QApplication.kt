package com.example.mvvm

import android.os.Build
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class QApplication {
    companion object {
        private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(CommonConstants.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor {
                it.proceed(
                    it.request().newBuilder()
                        .build()
                )
            }
            .addInterceptor(Interceptor { chain ->
                val request: Request = chain.request()
                val response = chain.proceed(request)

                // todo deal with the issues the way you need to
                if (response.code == HttpStatusCode.HTTP_UNAUTHORIZED) {
                    return@Interceptor response
                }
                response
            })
            .build()

        val CATELOGREADER_RETROFIT = Retrofit.Builder()
            .baseUrl("https://catalog-reader.qcoom.com/api/v1/category/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(CommonConstants.DEFAULT_NON_NULL_GSON))
            .build()

        val CATELOGREADER_RETROFIT2 = Retrofit.Builder()
            .baseUrl("https://catalog-reader.qcoom.com/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(CommonConstants.DEFAULT_NON_NULL_GSON))
            .build()
    }

}