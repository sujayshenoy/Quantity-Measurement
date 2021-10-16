package com.example.quantitymeasurement

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quantitymeasurement.databinding.ActivityMainBinding
import com.example.quantitymeasurement.fragments.AddQuantitiesFragment
import com.example.quantitymeasurement.fragments.ConvertQuantityFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertQuantityButton.setOnClickListener {
            switchFragment(ConvertQuantityFragment())
        }

        binding.addQuantitiesButton.setOnClickListener{
            switchFragment(AddQuantitiesFragment())
        }

        switchFragment(ConvertQuantityFragment())
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}