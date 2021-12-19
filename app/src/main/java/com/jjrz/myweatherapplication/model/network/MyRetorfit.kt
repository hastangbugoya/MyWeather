package com.jjrz.myweatherapplication.model.network

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.jjrz.myweatherapplication.BuildConfig
import com.jjrz.myweatherapplication.model.Forecast
import com.jjrz.myweatherapplication.utility.DebugHelper.Companion.LogKitty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.coroutines.coroutineContext

object MyRetorfit {

    private val ACTION_CUSTOM_BROADCAST =
        BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
    
    var myForecast = MutableLiveData<Forecast?>().apply {
        value = null
    }

    init {

    }

    fun getWeather(myCity: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MyWeatherService::class.java)
        val call = service.getCityWeather(myCity, "65d00499677e59496ca2f318eb68c049")
        LogKitty("Retrofit1")
        call.enqueue(object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                if (response.code() == 200) {
                    LogKitty("Assigning value to myForecast")
                    myForecast.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                myForecast.postValue(null)
                LogKitty(t.toString())
            }
        })
        LogKitty("Retrofit2")
    }

    interface MyWeatherService {
        @GET("forecast") //"forecast?q={city}&appid={api key}"
        fun getCityWeather(@Query("q") myCity: String, @Query("appid") appID: String): Call<Forecast>
    }

    fun notEmpty() : Boolean {
        return myForecast.let { true } ?: false
    }
}