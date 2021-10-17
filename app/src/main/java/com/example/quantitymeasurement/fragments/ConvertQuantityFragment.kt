package com.example.quantitymeasurement.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.databinding.ConvertQuantityFragmentBinding
import com.example.quantitymeasurement.util.QuantityConverter
import com.example.quantitymeasurement.util.Utilities

class ConvertQuantityFragment : Fragment(R.layout.convert_quantity_fragment) {
    private lateinit var binding: ConvertQuantityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ConvertQuantityFragmentBinding.bind(view)

        var arrayAdapter = ArrayAdapter.createFromResource(requireContext(),
            R.array.quantities,android.R.layout.simple_spinner_dropdown_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.quantitySpinner.adapter = arrayAdapter

        attachListeners()
    }

    private fun attachListeners() {
        binding.quantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedQuantity = binding.quantitySpinner.selectedItem.toString()
                changeSpinners()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.measureSpinnerL.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                fromQuantity = binding.measureSpinnerL.selectedItem.toString()
                convertQuantities()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.measureSpinnerR.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                toQuantity = binding.measureSpinnerR.selectedItem.toString()
                convertQuantities()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.fromQuantity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()){
                    value = 0f
                    return
                }
                value = p0.toString().toFloat()
                convertQuantities()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    fun convertQuantities() {
        val result = QuantityConverter.convertTo(value,selectedQuantity,fromQuantity,toQuantity)
        binding.toQuantity.setText(result.toString())
    }

    fun changeSpinners() {
        var measureAdapter:ArrayAdapter<CharSequence> = Utilities.createMeasureAdapterFromQuantity(requireContext(),
            selectedQuantity
        )
        binding.measureSpinnerL.adapter = measureAdapter
        binding.measureSpinnerR.adapter = measureAdapter
    }

    companion object {
        private var value = 0f
        private var selectedQuantity = "Length"
        private var fromQuantity = "Kilometre"
        private var toQuantity = "Kilometre"
    }
}