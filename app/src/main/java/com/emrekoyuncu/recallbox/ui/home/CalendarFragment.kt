package com.emrekoyuncu.recallbox.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentCalendarBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.muddzdev.styleabletoast.StyleableToast
import java.util.*

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: FirebaseDatabase


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
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

        var getdata = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                user?.let {
                    val uid = user.uid
                    for(i in snapshot.children){
                        var medicine_hour = i.child(uid).child("Medicines").child(count.toString()).child("medicine_hour").getValue()
                        var medicine_name = i.child(uid).child("Medicines").child(count.toString()).child("medicine_name").getValue()
                        var medicine_day = i.child(uid).child("Medicines").child(count.toString()).child("medicine_day").getValue()
                        sb.append("$medicine_name $medicine_day $medicine_hour\n")
                    }
                    _binding?.medicineInfo?.setText(sb)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }
        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)


        user?.let {
            val uid = user.uid
            _binding?.medicineDeleteButton?.setOnClickListener{
                database.child("Users").child(uid).child("Medicines").child(count.toString()).removeValue()
                StyleableToast.makeText(requireContext(), "İlaç silindi!", Toast.LENGTH_LONG,R.style.successToast).show()
            }
        }



    }

}