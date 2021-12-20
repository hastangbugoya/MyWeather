package com.jjrz.myweatherapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.jjrz.myweatherapplication.databinding.ActivityForecastDetailBinding
import com.jjrz.myweatherapplication.model.Forecasts

class ForecastDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityForecastDetailBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        val intent = this.intent
        val bundle = intent.extras
        val l = bundle?.getSerializable("value") as Forecasts?
        binding.textTest.text = l.toString()
        val temp = l?.main?.temp.toString()
        supportActionBar?.title = bundle?.getCharSequence("city_name")
        binding.textTemp.text = String.format("%.2fC",l?.main?.temp?.minus(273.15))
        binding.textMain.text = l?.weather?.get(0)?.main
        binding.textFeels.text = String.format("Feels like : %.2fC",l?.main?.feelsLike?.minus(273.15))
        binding.textDesc.text =  l?.weather?.get(0)?.description

    }
}