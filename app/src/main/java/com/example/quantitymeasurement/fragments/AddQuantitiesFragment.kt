package com.example.quantitymeasurement.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.R
import com.example.quantitymeasurement.databinding.AddQuantityFragmentBinding
import com.example.quantitymeasurement.util.QuantityConverter
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
                selectedQuantity = binding.addQuantitySpinner.selectedItem.toString()
                changeSpinners()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.measureQuantityL.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                quantity1 = binding.measureQuantityL.selectedItem.toString()
                addQuantities()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.measureQuantityM.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                quantity2 = binding.measureQuantityM.selectedItem.toString()
                addQuantities()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.measureQuantityR.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                toQuantity = binding.measureQuantityR.selectedItem.toString()
                addQuantities()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.quantity1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()){
                    lvalue = 0f
                    return
                }
                lvalue = p0.toString().toFloat()
                addQuantities()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.quantity2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()){
                    rvalue = 0f
                    return
                }
                rvalue = p0.toString().toFloat()
                addQuantities()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    fun addQuantities(){
        val result = QuantityConverter.addQuantites(selectedQuantity, quantity1, quantity2, toQuantity, lvalue,
            rvalue)
        binding.resultQuantity.setText(result.toString())
    }

    fun changeSpinners() {
        var measureAdapter:ArrayAdapter<CharSequence> = Utilities.createMeasureAdapterFromQuantity(requireContext(),
            selectedQuantity
        )
        binding.measureQuantityL.adapter = measureAdapter
        binding.measureQuantityM.adapter = measureAdapter
        binding.measureQuantityR.adapter = measureAdapter
    }

    companion object {
        private var selectedQuantity = "Length"
        private var quantity1 = "Kilometre"
        private var quantity2 = "Kilometre"
        private var toQuantity = "Kilometre"
        private var lvalue = 0f
        private var rvalue = 0f
    }
}