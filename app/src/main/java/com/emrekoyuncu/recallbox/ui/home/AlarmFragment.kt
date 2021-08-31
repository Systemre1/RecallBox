package com.emrekoyuncu.recallbox.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.droidsonroids.gif.GifImageView
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentAlarmBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.muddzdev.styleabletoast.StyleableToast
import java.util.*

class AlarmFragment : Fragment() {

    private var _binding: FragmentAlarmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Firebase.auth.currentUser
        val database = FirebaseDatabase.getInstance().reference
        var count : Int = 1

        _binding?.medicineSaveBtn?.setOnClickListener {
            val medicine_name = _binding?.medicineName?.text.toString().trim()
            val medicine_day = _binding?.medicineDay?.text.toString().toUpperCase(Locale.ROOT).trim()
            val medicine_hour = _binding?.medicineHour?.text.toString().trim()

            if(medicine_name.isEmpty() || medicine_day.isEmpty() || medicine_hour.isEmpty()){
                if (medicine_name.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen ilaç ismi giriniz!",
                        Toast.LENGTH_LONG,R.style.warningToast).show()
                if (medicine_day.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen gün giriniz!",
                        Toast.LENGTH_LONG,R.style.warningToast).show()
                if (medicine_hour.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen saat giriniz!",
                        Toast.LENGTH_LONG,R.style.warningToast).show()
                return@setOnClickListener
            }else{
                user?.let {
                    val uid = user.uid
                    database.child("Users").child(uid).child("Medicines").child(count.toString()).setValue(Medicine(medicine_name, medicine_day, medicine_hour))
                    count++
                }

                StyleableToast.makeText(requireContext(), "Alarm başarıyla oluşturuldu!", Toast.LENGTH_LONG,R.style.successToast).show()

            }

        }


    }


}