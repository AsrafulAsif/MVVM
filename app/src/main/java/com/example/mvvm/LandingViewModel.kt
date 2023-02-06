package com.example.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LandingViewModel : ViewModel() {
    var movieLiveData = MutableLiveData<Data>()
    private val groceryService = RetrofitConfig.groceryService
    private var categoryCall: Call<Data>? = null
    fun getPopularMovies() {
        categoryCall = groceryService.getAllCategories("Q_FOOD")
        categoryCall?.enqueue(object  : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.body()!=null){
                    movieLiveData.postValue(response.body())
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : MutableLiveData<Data> {
        return movieLiveData
    }
}