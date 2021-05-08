package com.example.ltbudget

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class HomepageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        var TAG = "Message"
        val fName = findViewById<TextView>(R.id.firstnameText)
        val lName = findViewById<TextView>(R.id.lastnameText)
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        //TextView to String



    }



}