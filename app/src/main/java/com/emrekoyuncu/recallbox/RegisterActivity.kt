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

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth :FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
    }

    fun saveRegisterBtn(view: View) {

        val email = registerMail.text.toString()
        val password = registerPassword.text.toString()
        val controlpassword = registerControlPassword.text.toString()

        if(email.isEmpty() || password.isEmpty() || controlpassword.isEmpty()){
            if (email.isEmpty())
                StyleableToast.makeText(this,"Lütfen E-Posta Adresinizi giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            if (password.isEmpty())
                StyleableToast.makeText(this,"Lütfen şifre giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            if (controlpassword.isEmpty())
                StyleableToast.makeText(this,"Lütfen şifrenizi ikinci kez giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
            return
        }

        if (password == controlpassword){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(!it.isSuccessful) {
                    return@addOnCompleteListener
                }

                Log.d("Main", "Successfully created user with User uid: ${it.result?.user?.uid}")

                if (it.isSuccessful) {
                    StyleableToast.makeText(applicationContext, "Kayıt Başarılı!",Toast.LENGTH_LONG,R.style.successToast).show()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }.addOnFailureListener {
                StyleableToast.makeText(applicationContext,"Hata! ${it.message}",Toast.LENGTH_LONG,R.style.errorToast).show()
            }
        }else {
            StyleableToast.makeText(this,"Girdiğiniz şifreler aynı değil. Lütfen tekrar giriniz!",Toast.LENGTH_LONG,R.style.warningToast).show()
        }

        Log.d("MainActivity","Email is: " + email)
        Log.d("MainActivity","Password: $password")



    }
}