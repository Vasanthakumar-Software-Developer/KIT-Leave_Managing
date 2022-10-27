package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Submit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        supportActionBar?.hide()
        val name=findViewById<EditText>(R.id.namw1).text
        val year=findViewById<EditText>(R.id.namw2).text
        val dep=findViewById<EditText>(R.id.namw3).text
        val per=findViewById<EditText>(R.id.namw5).text
        val noper=findViewById<EditText>(R.id.namw4).text
        val button=findViewById<Button>(R.id.button)
        val database=Firebase.database.getReference("Faculty")
        val name1=intent.getStringExtra("name1")


        button.setOnClickListener {
                if (name.isNotEmpty() && year.isNotEmpty() && dep.isNotEmpty() && per.isNotEmpty() && noper.isNotEmpty()) {
                    database.child("$name1").child("AName").setValue("$name").addOnSuccessListener {
                        database.child("$name1").child("AYear").setValue("$year")
                        database.child("$name1").child("Adeparment").setValue("$dep")
                        database.child("$name1").child("Anoofhours").setValue("$noper")
                        database.child("$name1").child("Aperiods").setValue("$per")
                        Toast.makeText(this, "Successfully Submitted", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, select::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
