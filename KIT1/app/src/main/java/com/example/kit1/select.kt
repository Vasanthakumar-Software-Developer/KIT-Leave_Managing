package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_select.*

class select : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
        supportActionBar?.hide()
        val button=findViewById<Button>(R.id.button4)
            button.setOnClickListener{
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
        }
        val button1=findViewById<Button>(R.id.button2)
        button1.setOnClickListener{
            val intent= Intent(this,Pin::class.java)
            startActivity(intent)
            finish()
        }
    }
}