package com.example.quantitymeasurement.util

object QuantityConverter {
    fun convertTo(value: Float,parentQuant : String ,fromQuant : String , toQuant : String) : Float {
        return when(parentQuant){
            "Length" -> convertLength(value,fromQuant,toQuant)
            "Weight" -> convertWeight(value,fromQuant,toQuant)
            "Volume" -> convertVolume(value,fromQuant,toQuant)
            "Temperature" -> convertTemperature(value,fromQuant,toQuant)
            else -> value
        }
    }

    fun addQuantites(parentQuant: String,quant1:String,quant2:String,toQuant:String,lValue:Float,rValue:Float) : Float {
        val quant1ConvertedValue = convertTo(lValue,parentQuant,quant1,toQuant)
        val quant2ConvertedValue = convertTo(rValue,parentQuant,quant2,toQuant)
        return quant1ConvertedValue + quant2ConvertedValue
    }

    private fun convertTemperature(value: Float, fromQuant: String, toQuant: String): Float {
        return when(fromQuant) {
            "Celsius" -> convertCelsius(value,toQuant)
            "Fahrenheit" -> convertFahrenheit(value,toQuant)
            "Kelvin" -> convertKelvin(value,toQuant)
            else -> value
        }
    }

    private fun convertVolume(value: Float, fromQuant: String, toQuant: String): Float {
        return when(fromQuant) {
            "Gallon" -> convertGallon(value,toQuant)
            "Litre" -> convertLitre(value,toQuant)
            "Millilitre" -> convertMillilitre(value,toQuant)
            else -> value
        }
    }



    private fun convertWeight(value: Float, fromQuant: String, toQuant: String): Float {
        return when(fromQuant) {
            "Ton" -> convertTon(value,toQuant)
            "Kilogram" -> convertKiloGram(value,toQuant)
            "Gram" -> convertGram(value,toQuant)
            else -> value
        }
    }



    private fun convertLength(value: Float, fromQuant: String, toQuant: String) : Float {
        return when(fromQuant) {
            "Kilometre" -> convertKiloMetre(value,toQuant)
            "Metre" -> convertMetre(value,toQuant)
            "Centimetre" -> convertCentiMetre(value,toQuant)
            else -> value
        }
    }

    private fun convertCentiMetre(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Metre" -> value/100
            "Kilometre" -> value/100000
            else -> value
        }
    }

    private fun convertMetre(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Centimetre" -> value*100
            "Kilometre" -> value/1000
            else -> value
        }
    }

    private fun convertKiloMetre(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Centimetre" -> value*100000
            "Metre" -> value*1000
            else -> value
        }
    }

    private fun convertKelvin(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Celsius" -> value-273.15f
            "Fahrenheit" -> ((value-273.15f)*(9f/5f)+32)
            else -> value
        }
    }

    private fun convertFahrenheit(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Celsius" -> ((value-32)*(5f/9f))
            "Kelvin" -> ((value-32)*(5f/9f)+273.15f)
            else -> value
        }
    }

    private fun convertCelsius(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Fahrenheit" -> (value * 9f/5f) + 32
            "Kelvin" -> value+273.15f
            else -> value
        }
    }

    private fun convertMillilitre(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Litre" -> value/1000
            "Gallon" -> value/3785
            else -> value
        }
    }

    private fun convertLitre(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Millilitre" -> value*1000
            "Gallon" -> value/3.785f
            else -> value
        }
    }

    private fun convertGallon(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Millilitre" -> value*3785
            "Litre" -> value*3.785f
            else -> value
        }
    }

    private fun convertGram(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Kilogram" -> value/1000
            "Ton" -> value/907185
            else -> value
        }
    }

    private fun convertKiloGram(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Gram" -> value*1000
            "Ton" -> value/907
            else -> value
        }
    }

    private fun convertTon(value: Float, toQuant: String): Float {
        return when(toQuant) {
            "Kilogram" -> value*907
            "Gram" -> value*907185
            else -> value
        }
    }
}