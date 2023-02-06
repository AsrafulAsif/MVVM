package com.example.mvvm



object RetrofitConfig {
//    val api : Service  {
//        Retrofit.Builder()
//            .baseUrl("https://staging-catalog-reader.qcoom.com:443/api/v1/category/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(Service::class.java)
//    }
val groceryService = QApplication.CATELOGREADER_RETROFIT.create(Service::class.java)

}