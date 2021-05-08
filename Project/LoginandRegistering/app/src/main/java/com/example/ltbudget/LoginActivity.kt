package com.example.ltbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()

        //creating buttons
        val forgotInfo = findViewById<Button>(R.id.forgotPassword)
        val goBackButton = findViewById<Button>(R.id.returnMainPage)
        val okButton = findViewById<Button>(R.id.okButtonLogin)

        //Edit text
        val usrname = findViewById<EditText>(R.id.loginUsername)
        val password = findViewById<EditText>(R.id.passwordLogin)
        //button changed into a new activity
        forgotInfo.setOnClickListener {
            val ForgotAct = Intent(this, ForgotInfoActivity::class.java)
            startActivity(ForgotAct)
        }

        //button changed into a new activity
        goBackButton.setOnClickListener {
            val goBackAct = Intent(this, MainActivity::class.java)
            startActivity(goBackAct)
        }

        okButton.setOnClickListener{
            val username: String = usrname.text.toString()
            val pwString:String = password.text.toString()
            val TAG = "message"

            //Reference: https://stackoverflow.com/questions/52861391/firestore-checking-if-username-already-exists
            val userRef = db.collection("user")
            val usernameCheck = userRef.whereEqualTo("username", username)
            usernameCheck.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        if (document.exists()) {
                            val passwordDoc = document.getString("password")
                            if(passwordDoc == pwString){
                                val homePageAct = Intent(this, HomepageActivity::class.java)
                                startActivity(homePageAct)
                                Toast.makeText(this, "Login is successful!", Toast.LENGTH_LONG).show()
                            }
                            else{
                                password.error = " Incorrect password. Please try again."
                                Toast.makeText(this, "Login is NOT successful!", Toast.LENGTH_LONG).show()
                            }
                        }
                        else {
                            usrname.error = "Username does not exist. Please try again."
                            Toast.makeText(this, "Login is NOT successful!", Toast.LENGTH_LONG).show()
                            Log.d(TAG, "Username does not exist.")
                        }
                    }

                } else {
                    Log.d("TAG", "Error getting documents: ", task.exception)
                }
            }
        }
    }
}