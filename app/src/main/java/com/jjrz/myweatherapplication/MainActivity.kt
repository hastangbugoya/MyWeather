package com.jjrz.myweatherapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.jjrz.myweatherapplication.databinding.ActivityMainBinding
import com.jjrz.myweatherapplication.model.network.MyRetorfit
import com.jjrz.myweatherapplication.utility.DebugHelper.Companion.LogKitty
import com.jjrz.myweatherapplication.view.ForecastsListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val view = binding.root
        setContentView(view)

        MyRetorfit.myForecast.observe(this, {f ->
            LogKitty("Change detected")
            LogKitty("test")
            if (MyRetorfit.myForecast.value != null) {
                val intent = Intent(this, ForecastsListActivity::class.java).apply {
                    val bundle = Bundle()
                    bundle.putSerializable("value", f)
                    this.putExtras(bundle)
                }
                startActivity(intent)
            }
        })

        binding.buttonGetIt.setOnClickListener() {
            if (binding.edittextCity.text.count() > 0)
            {
                MyRetorfit.getWeather(binding.edittextCity.text.toString())
            }
        }
    }

}

