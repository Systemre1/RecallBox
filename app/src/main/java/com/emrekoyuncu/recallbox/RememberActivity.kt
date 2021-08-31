package com.emrekoyuncu.recallbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_remember.*

class RememberActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remember)

        auth = FirebaseAuth.getInstance()

    }

    fun saveRememberBtn(view: View) {

        val email = rememberMail.text.toString()

        if(email.isEmpty()){
            StyleableToast.makeText(this,"Lütfen E-Posta Adresinizi giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            return
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(!it.isSuccessful) {
                return@addOnCompleteListener
            }
            if (it.isSuccessful) {
                StyleableToast.makeText(applicationContext, "${auth.currentUser?.email.toString()} adresine mail gönderildi!",Toast.LENGTH_LONG,R.style.successToast).show()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            StyleableToast.makeText(applicationContext,"Hata! ${it.message}",Toast.LENGTH_LONG,R.style.errorToast).show()
        }

    }
}