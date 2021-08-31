package com.emrekoyuncu.recallbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muddzdev.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_remember.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()


    }

    fun login(view: View) {
        email = loginMail.text.toString()
        password = loginPassword.text.toString()

        if(email.isEmpty() || password.isEmpty()){
            if (email.isEmpty())
                StyleableToast.makeText(this,"Lütfen E-Posta Adresinizi giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            if (password.isEmpty())
                StyleableToast.makeText(this,"Lütfen şifre giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                StyleableToast.makeText(applicationContext, "Hoş geldiniz! ${auth.currentUser?.email.toString()}",Toast.LENGTH_LONG,R.style.successToast).show()
                val intent = Intent(applicationContext, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            StyleableToast.makeText(applicationContext,"Hata! ${it.message}",Toast.LENGTH_LONG,R.style.errorToast).show()
        }
    }


    fun register(view: View) {
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun forgetPass(view: View) {
        val intent = Intent(applicationContext, RememberActivity::class.java)
        startActivity(intent)
    }


    fun rememberMeOnClick(view: View) {

    }
}

