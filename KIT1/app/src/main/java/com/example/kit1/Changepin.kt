package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Changepin : AppCompatActivity() {
    private lateinit var b:EditText
    private lateinit var a:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepin)
        val pinchange = intent.getStringExtra("pinchange1")
        Toast.makeText(this, "$pinchange ", Toast.LENGTH_SHORT).show()
        val a=findViewById<EditText>(R.id.editTextTextPersonName).text
        val b=findViewById<EditText>(R.id.editTextTextPersonName3).text
        val btn=findViewById<Button>(R.id.button9)
        supportActionBar?.hide()

        btn.setOnClickListener {

            val dbf = FirebaseDatabase.getInstance().getReference("password")

            Toast.makeText(this, "$pinchange ", Toast.LENGTH_SHORT).show()
            dbf.child("$pinchange").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    dbf.child("$pinchange").setValue("$b")
                    Toast.makeText(this, "Password changed successful", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, Pin::class.java)
                    startActivity(i)
                }

            }
        }

    }
}