package com.example.class02

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private var TAG_LOGIN = "Login"
    private var USER_NAME = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        auth = Firebase.auth

    }

    fun submitRegister(view: View) {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivityForResult(intent, 1)
    }

    fun submitLogin(view: View) {
        var email = findViewById<EditText>(R.id.main_login_email).text.toString()
        var password = findViewById<EditText>(R.id.main_login_password).text.toString()

        if (validateLogin(email, password)) {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successful Login", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent);

                    } else {
                        Toast.makeText(this, "Failed Login", Toast.LENGTH_SHORT).show()

                    }
                }
            //Toast.makeText(this, R.string.login_success, Toast.LENGTH_LONG).show()
            //Toast.makeText(this, USER_NAME, Toast.LENGTH_LONG).show()

        } else {
            alertDialog()
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        var validLogin: Boolean

        validLogin = if (validEmailPattern(email) && password.length > 5) {
            Log.d(TAG_LOGIN, getString(R.string.login_success))
            true

        } else {
            Log.d(TAG_LOGIN, getString(R.string.login_failed))
            false
        }
        return validLogin
    }

    private fun validEmailPattern(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun alertDialog() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.general_error))
        builder.setMessage(getString(R.string.login_error_on_access))
        builder.setPositiveButton(getString(R.string.general_ok)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                val resultMail = data?.getStringExtra("email").toString()
                val resultName = data?.getStringExtra("name").toString()
                USER_NAME = resultName

                if (!resultMail.isNullOrEmpty()) {
                    val emailText = findViewById<EditText>(R.id.main_login_email)
                    emailText.setText(resultMail)
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d(TAG_LOGIN, "We are not on the intent 1")
            }
        }
    }
}