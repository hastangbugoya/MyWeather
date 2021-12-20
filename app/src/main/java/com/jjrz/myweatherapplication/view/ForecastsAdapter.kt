package com.jjrz.myweatherapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.jjrz.myweatherapplication.databinding.ForecastsListitemBinding
import com.jjrz.myweatherapplication.model.Forecast
import com.jjrz.myweatherapplication.model.network.MyRetorfit

class ForecastsAdapter : RecyclerView.Adapter<ForecastsAdapter.ForecastsViewHolder>() {
    var myForecast: Forecast? = null


    fun updateForecast(f: Forecast?) {
        myForecast = f
        notifyDataSetChanged()
    }

    fun ForecastsAdapter(f: Forecast?) {
        myForecast = f
        notifyDataSetChanged()
    }

    inner class ForecastsViewHolder(binding: ForecastsListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: ForecastsListitemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastsViewHolder {
        val binding = ForecastsListitemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForecastsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastsViewHolder, position: Int) {
        with(holder.binding) {
            textWeather.text =
                MyRetorfit.myForecast.value?.list?.get(position)?.weather?.get(0)?.main
            textTemperature.text =
                String.format(
                    "%.2fC",
                    MyRetorfit.myForecast.value?.list?.get(position)?.main?.temp?.minus(273.15)
                )
            buttonText.setOnClickListener {
                val intent = Intent(buttonText.context, ForecastDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable(
                    "value",
                    MyRetorfit.myForecast.value?.list?.get(position)
                )
                bundle.putCharSequence("city_name", MyRetorfit.myForecast.value?.city?.name)
                intent.putExtras(bundle)
                startActivity(buttonText.context, intent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return MyRetorfit.myForecast.value?.list?.size ?: 0
    }
}