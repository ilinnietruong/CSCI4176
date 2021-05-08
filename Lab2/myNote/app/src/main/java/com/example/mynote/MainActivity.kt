package com.example.mynote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.addButton).setOnClickListener{
            Snackbar.make(it,"Task added.", Snackbar.LENGTH_LONG).setAction("Action1", null).show()
        }
    }
}







