package com.example.mvvm
//new
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoplistViewModel : ViewModel() {
    var movieLiveData2 = MutableLiveData<ShopResponse>()
    private val foodService = RetrofitConfig.foodService
    private var restaurantAroundYouCall: Call<ShopResponse>? = null

    fun requestForRestaurantAroundYou(type: String, latitude: Double?, longitude: Double?, i: Int) {
        try {

            restaurantAroundYouCall = foodService.getRestaurantAroundMe(
                type,
                latitude.toString(),
                longitude.toString(),
                0,
                200

            )

            restaurantAroundYouCall?.enqueue(object : Callback<ShopResponse> {
                override fun onResponse(
                    call: Call<ShopResponse>,
                    response: Response<ShopResponse>
                ) {
                    if (response.body()!=null){
                        movieLiveData2.postValue(response.body())
                    }
                    else{
                        return
                    }
                    Log.d("asif",response.body()?.shops.toString())

                }

                override fun onFailure(call: Call<ShopResponse>, t: Throwable) {
                    Log.d("TAG",t.message.toString())

                }
            })


        } catch (ex: Exception) {
            Log.d("asif", ex.message!!)
        }

    }
    fun observeMovieLiveData() : MutableLiveData<ShopResponse> {
        return movieLiveData2
    }
}