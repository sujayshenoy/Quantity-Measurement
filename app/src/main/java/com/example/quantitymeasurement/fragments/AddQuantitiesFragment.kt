package com.example.quantitymeasurement.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.databinding.AddQuantityFragmentBinding
import com.example.quantitymeasurement.util.Utilities

class AddQuantitiesFragment : Fragment(R.layout.add_quantity_fragment) {
    private lateinit var binding: AddQuantityFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = AddQuantityFragmentBinding.bind(view)

        var quantityAdapter = ArrayAdapter.createFromResource(requireContext(),R.array.quantities,android.R.layout.simple_spinner_dropdown_item)
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.addQuantitySpinner.adapter = quantityAdapter

        binding.addQuantitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                changeSpinners(binding.addQuantitySpinner.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                changeSpinners("Length")
            }
        }
    }

    fun changeSpinners(selectedQuantity : String) {
        var measureAdapter:ArrayAdapter<CharSequence> = Utilities.createMeasureAdapterFromQuantity(requireContext(),
            selectedQuantity
        )
        binding.measureQuantityL.adapter = measureAdapter
        binding.measureQuantityM.adapter = measureAdapter
        binding.measureQuantityR.adapter = measureAdapter
    }
}