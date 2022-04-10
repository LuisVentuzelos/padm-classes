package com.example.class02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    private var TAG_HOME = "Home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()
        var uid = auth.currentUser!!.uid
        db.collection("users").document(uid).addSnapshotListener(EventListener { value, error ->
            val name = value!!["name"].toString()
            findViewById<TextView>(R.id.main_home_name_et).setText(name);
        })
    }

    fun updateUserName(view: View) {

        auth = FirebaseAuth.getInstance()
        var uid = auth.currentUser!!.uid

        val nameOnUpdateText = findViewById<TextView>(R.id.main_home_name_et).text.toString();
        Log.d(TAG_HOME, nameOnUpdateText)

        val userCollection = db.collection("users").document(uid)

        userCollection
            .update("name", nameOnUpdateText)
            .addOnSuccessListener {
                Log.d(TAG_HOME, "DocumentSnapshot successfully updated!")
                Toast.makeText(this, "Successful Updated", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(TAG_HOME, "Error updating document", e)
                Toast.makeText(this, "Cannot Update the value", Toast.LENGTH_SHORT).show()

            }
    }
}