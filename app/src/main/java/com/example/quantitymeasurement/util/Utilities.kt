package com.example.quantitymeasurement.util

import android.content.Context
import android.widget.ArrayAdapter
import com.example.quantitymeasurement.R

object Utilities {
    fun createMeasureAdapterFromQuantity(context: Context, quantity: String): ArrayAdapter<CharSequence> {
        var array:Int = 0
        when(quantity) {
            "Length" -> {
                array = R.array.lengthMeasures
            }
            "Weight" -> {
                array = R.array.weightMeasures
            }
            "Volume" -> {
                array = R.array.volumeMeasures
            }
            "Temperature" -> {
                array = R.array.temperatureMeasures
            }
        }

        var measureAdapter = ArrayAdapter.createFromResource(context,array,android.R.layout.simple_spinner_dropdown_item)
        measureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        return measureAdapter
    }
}