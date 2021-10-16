package com.example.quantitymeasurement.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.databinding.ConvertQuantityFragmentBinding
import com.example.quantitymeasurement.util.QuantityConverter

class ConvertQuantityFragment : Fragment(R.layout.convert_quantity_fragment) {
    private lateinit var binding: ConvertQuantityFragmentBinding
    private lateinit var selectedQuantity : String

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

        binding.quantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selectedQuantity = binding.quantitySpinner.selectedItem.toString()
                changeSpinners()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                selectedQuantity = "Length"
                changeSpinners()
            }
        }

    }

    fun changeSpinners() {
        var measureAdapter:ArrayAdapter<CharSequence> = QuantityConverter.createMeasureAdapterFromQuantity(requireContext(),
            selectedQuantity
        )
        binding.measureSpinnerL.adapter = measureAdapter
        binding.measureSpinnerR.adapter = measureAdapter
    }
}