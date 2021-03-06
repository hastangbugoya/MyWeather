package com.jjrz.myweatherapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.jjrz.myweatherapplication.databinding.ActivityForecastsListBinding
import com.jjrz.myweatherapplication.model.Forecast


class ForecastsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityForecastsListBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)
        var adapter = ForecastsAdapter()
        binding.recyclerviewForecasts.adapter = adapter
        val intent = this.intent
        val bundle = intent.extras
        val l = bundle?.getSerializable("value") as Forecast
        supportActionBar?.title = l?.city?.name.let { it } ?: "Error"
        adapter.updateForecast(l)
    }
}