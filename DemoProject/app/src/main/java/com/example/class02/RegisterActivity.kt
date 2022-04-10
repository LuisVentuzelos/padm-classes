package com.example.class02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.class02.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    private var TAG_REGISTER = "Register"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

    }


    fun goBack(view: View) {
        this.finish()
    }

    fun submitRegister(view: View) {
        val returnIntent = Intent()
        val name = findViewById<EditText>(R.id.register_nome_et).text.toString()
        val email = findViewById<EditText>(R.id.register_email_et).text.toString()
        var password = findViewById<EditText>(R.id.register_password_et).text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val uid = task.result.user?.uid; //Unique Identifier

                    if (uid != null) {
                        db.collection("users").document(uid)
                            .set(User(name = name))
                            .addOnSuccessListener {
                                Log.d(
                                    TAG_REGISTER,
                                    "DocumentSnapshot successfully written!"
                                )
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    TAG_REGISTER,
                                    "Error writing document",
                                    e
                                )
                            }
                    }


                    Toast.makeText(this, "Successful Register", Toast.LENGTH_SHORT).show()

                    Log.d(TAG_REGISTER, name)
                    returnIntent.putExtra("name", name)
                    returnIntent.putExtra("email", email)

                    setResult(Activity.RESULT_OK, returnIntent)

                } else {
                    Toast.makeText(this, "Failed Register", Toast.LENGTH_SHORT).show()
                }

                this.finish()
            }

    }
}