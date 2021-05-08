package com.example.ltbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ForgotInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_info)
        val db = Firebase.firestore

        //Buttons
        //creating buttons
        val goBackLoginButton = findViewById<Button>(R.id.goBackLogin)
        val goBackRegisterButton = findViewById<Button>(R.id.registerAgain)
        val okButton = findViewById<Button>(R.id.okInfoButton)

        val email = findViewById<EditText>(R.id.retrieveInfo)
        val popupMessage = findViewById<TextView>(R.id.messageInfo)
        //button changed into a new activity
        goBackRegisterButton.setOnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
        goBackLoginButton.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }
        okButton.setOnClickListener {
            val emailString: String = email.text.toString()
            val TAG = "message"

            //Reference: https://stackoverflow.com/questions/52861391/firestore-checking-if-username-already-exists
            val allUsersRef = db.collection("user")
            val emailCheck = allUsersRef.whereEqualTo("e-mail", emailString)
            emailCheck.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        if (document.exists()) {
                            val userName = document.getString("username")
                            val password = document.getString("password")
                            popupMessage.text = "Your username is: $userName\n Your password is: $password"
                        }
                        else {
                            email.error = "Email does not exist. Please try again."
                            Log.d(TAG, "Email does not exist.")

                        }
                    }

                } else {
                    Log.d("TAG", "Error getting documents: ", task.exception)
                }
            }
        }
    }
}