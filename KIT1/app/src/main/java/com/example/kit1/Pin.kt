package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.w3c.dom.Text

class Pin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        supportActionBar?.hide()
        val a=findViewById<EditText>(R.id.editTextTextPassword).text
        val b=findViewById<Button>(R.id.button8)

        b.setOnClickListener{
            Toast.makeText(this,"*Enter$a valid pin*",Toast.LENGTH_SHORT).show()
            if("$a"=="CSE!10"){
                val intent= Intent(this,detail::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"*Enter valid pin*",Toast.LENGTH_SHORT).show()
            }
        }
    }
}