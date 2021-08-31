package com.emrekoyuncu.recallbox.health

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentCalorieBinding
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.fragment_calorie.*
import java.util.*


class CalorieFragment : Fragment() {


    private var _binding: FragmentCalorieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalorieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculateCalorie(){
        var calculateCalorie : Double = 0.0
        val weight = weight_edit_text.text.toString().toFloat()
        val height = height_edit_text.text.toString().toInt()
        val age = age_edit_text.text.toString().toInt()
        val gender = gender_edit_text.text.toString().toUpperCase(Locale.ROOT)
        if (gender=="ERKEK"){
            calculateCalorie = 66 + (13.7 * weight) + (5 * height) - (6.8 * age)
        }else if (gender=="KADIN"){
            calculateCalorie = 665 + (9.6 * weight) + (1.8 * height) - (4.7 * age)
        }

        calorie_text_view.text = calculateCalorie.toString()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.calculateButton?.setOnClickListener {
            if (weight_edit_text.text.isNotEmpty() && height_edit_text.text.isNotEmpty() && age_edit_text.text.isNotEmpty() && gender_edit_text.text.isNotEmpty()) {
                calculateCalorie()
            } else {
                StyleableToast.makeText(requireContext(),"Lütfen gerekli alanı giriniz!",
                    Toast.LENGTH_LONG,R.style.warningToast).show()
            }

        }
    }


}