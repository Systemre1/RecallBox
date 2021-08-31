package com.emrekoyuncu.recallbox.health

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentWaterBinding
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.fragment_water.*
import kotlinx.android.synthetic.main.fragment_water.weight_edit_text
import java.util.*
import kotlin.math.roundToInt


class WaterFragment : Fragment() {

    private var _binding: FragmentWaterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun calculateWater(){

        var waterCalculate : Double = 0.0
        var waterCalculateMl : Double = 0.0
        var glassCalculate : Int
        val weight = weight_edit_text.text.toString().toFloat()
        val gender = gender_edit_text.text.toString().toUpperCase(Locale.ROOT)

        fun calculate(){
            waterCalculate = weight * (0.033)
            waterCalculateMl = (weight * (33)).toDouble()
            val waterCalculateString = String.format("%.1f", waterCalculate).toDouble()
            val waterCalculateMlString = String.format("%.0f", waterCalculateMl).toInt()
            water_text_view.text = ("$waterCalculateMlString ml/gün ($waterCalculateString Litre)")
            glassCalculate= (waterCalculate/0.250).roundToInt()
            glass_text_view.text=("$glassCalculate Bardak (250ml'lik)")
        }

        if (gender=="ERKEK"){
            calculate()
        }else if (gender=="KADIN"){
            calculate()
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding?.calculateButton?.setOnClickListener {
            if (weight_edit_text.text.isNotEmpty() && gender_edit_text.text.isNotEmpty() ) {
                calculateWater()
            } else {
                StyleableToast.makeText(requireContext(), "Lütfen gerekli alanı giriniz!", Toast.LENGTH_LONG, R.style.warningToast).show()
            }


        }


    }


}