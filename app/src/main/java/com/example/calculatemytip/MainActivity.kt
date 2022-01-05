package com.example.calculatemytip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculatemytip.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipAmount.text =""
            return
        }

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty -> 0.20
            R.id.eighteen -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage*cost

        if(binding.roundupSwitch.isChecked){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}