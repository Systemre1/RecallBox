package com.emrekoyuncu.recallbox.ui.home

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.emrekoyuncu.recallbox.R
import com.emrekoyuncu.recallbox.databinding.FragmentProfilBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.fragment_profil.*
import java.util.*

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun selectPhoto(view: View) {
        Log.d("ProfilFragment","Try to show photo selector")

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {

            Log.d("ProfilFragment","Photo was selected")

            val url = data.data

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = Firebase.auth.currentUser
        val database = FirebaseDatabase.getInstance().reference

        _binding?.profileSaveBtn?.setOnClickListener {
            val name = _binding?.userName?.text.toString().trim()
            val surname = _binding?.userSurname?.text.toString().trim()
            val gender = _binding?.userGender?.text.toString().toUpperCase(Locale.ROOT).trim()
            val length = _binding?.userlength?.text.toString().trim()
            val weight = _binding?.userWeight?.text.toString().trim()

            if(name.isEmpty() || surname.isEmpty() || gender.isEmpty() || length.isEmpty() || weight.isEmpty()){
                if (name.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen isminizi giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
                if (surname.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen soyisminizi giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
                if (gender.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen cinsiyetinizi giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
                if (length.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen boyunuzu giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
                if (weight.isEmpty())
                    StyleableToast.makeText(requireContext(),"Lütfen kilonuzu giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
                return@setOnClickListener
            }else{
                user?.let {
                    val uid = user.uid
                    database.child("Users").child(uid).setValue(User(name, surname, gender, length, weight))
                }

                StyleableToast.makeText(requireContext(), "Kayıt Başarılı!",Toast.LENGTH_LONG,R.style.successToast).show()

            }

        }


    }



}

