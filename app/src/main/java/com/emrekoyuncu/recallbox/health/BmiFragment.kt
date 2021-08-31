package com.emrekoyuncu.recallbox.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentBmiBinding
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.fragment_bmi.*

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calculateBMI(){
        val weight = weight_edit_text.text.toString().toFloat()
        val height = height_edit_text.text.toString().toFloat()

        val myBMI = weight / (height * height)

        bmi_text_view.text = myBMI.toString()

        if (myBMI < 18.5) {
            bmi_image_view.setImageResource(R.drawable.underweight)
        } else if (myBMI >= 18.5 && myBMI < 24.9) {
            bmi_image_view.setImageResource(R.drawable.healthy)
        } else if (myBMI >= 24.9 && myBMI < 29.9) {
            bmi_image_view.setImageResource(R.drawable.overweight)
        } else if (myBMI >= 29.9) {
            bmi_image_view.setImageResource(R.drawable.obesity)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.calculateButton?.setOnClickListener {
            if (weight_edit_text.text.isNotEmpty() && height_edit_text.text.isNotEmpty()) {
                calculateBMI()
            } else {
                StyleableToast.makeText(requireContext(),"Lütfen gerekli alanı giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            }

        }
    }


}