package com.example.ltbudget

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val db = Firebase.firestore

        //creating buttons
        val backButton = findViewById<Button>(R.id.backButton)
        val confirmButton = findViewById<Button>(R.id.confirm)

        val TAG = "message"

        //edit text value
        val firstName = findViewById<EditText>(R.id.firstName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val usrname = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val pw1 = findViewById<EditText>(R.id.password1)
        val pw2 = findViewById<EditText>(R.id.password2)


        var usr: HashMap<String, String> = HashMap<String, String>()


        //button changed into a new activity
        backButton.setOnClickListener {

            val goBackAct = Intent(this, MainActivity::class.java)
            startActivity(goBackAct)
        }
        confirmButton.setOnClickListener {
            val fn: String = firstName.text.toString()
            val ln: String = lastName.text.toString()
            val em: String = email.text.toString()
            val un: String = usrname.text.toString()
            val password1: String = pw1.text.toString()
            val password2: String = pw2.text.toString()

            //call from Validation.kt class
            val registrationValidator = Validation(fn, ln, un, em, password1)

            //Determine if the names are valid()
            if (registrationValidator.isEmpty(registrationValidator.getFirstName())) {
                firstName.error = "Empty. Invalid first name.";
            }

            if (registrationValidator.isEmpty(registrationValidator.getLastName())) {
                lastName.error = "Empty. Invalid last name.";
            }

            if (registrationValidator.isEmpty(registrationValidator.getUsername())) {
                usrname.error = "Empty. Invalid username.";
            }

            //Determine whether the e-mail is Valid
            if (!registrationValidator.validEmail()) {
                email.error = "Invalid e-mail.Ex:johndoe@dal.ca";
            }


            //Determine whether the password is valid
            if (!registrationValidator.isValidPassword()) {
                //then check for what's wrong with password

                if (!registrationValidator.passwordLengthCheck()) {
                    pw1.error = "Password must be between 5 and 10 characters long.";
                } else {
                    pw1.error = "At least one upper case, one lower case, and one integer.";
                }
            }

            if (!registrationValidator.samePassword(password2)) {
                pw2.error = "Password does not match.";
            }

            if (registrationValidator.successfulRegister() && registrationValidator.samePassword(password2)) {
                            usr = hashMapOf(
                                "Firstname" to firstName.text.toString(),
                                "lastname" to lastName.text.toString(),
                                "e-mail" to email.text.toString(),
                                "password" to pw1.text.toString(),
                                "username" to usrname.text.toString()
                            )
                //Reference: https://cloud.google.com/firestore/docs/manage-data/add-data
                db.collection("user")
                    .add(usr)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
                Toast.makeText(this, "Register is successful!", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Added to firestore.")

            } else {
                Toast.makeText(this, "Register is NOT successful!", Toast.LENGTH_LONG).show()
                Log.d(TAG, "An error occured in Firestore.")
            }
        }
    }
}