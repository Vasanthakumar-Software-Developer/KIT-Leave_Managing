package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.view.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val l=resources.getStringArray(R.array.department)
        val arrayadapter=ArrayAdapter(this,R.layout.drobdown,l)
        val name=findViewById<EditText>(R.id.text1).text
        val des=findViewById<EditText>(R.id.text3).text
        val dep=findViewById<EditText>(R.id.text4).text
        val days=findViewById<EditText>(R.id.text5).text
        val pur=findViewById<EditText>(R.id.text6).text

       val database=Firebase.database.getReference("Faculty")
        val b=findViewById<Button>(R.id.button)
//        val key=database.push().key!!

      val calendar= Calendar.getInstance()
        val simple=SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date=simple.format(calendar.time)

        b.setOnClickListener {
            if(name.isNotEmpty() && des.isNotEmpty() && dep.isNotEmpty() && days.isNotEmpty() && pur.isNotEmpty()){
                database.child("$name").child("Name").setValue("$name").addOnSuccessListener {
                    database.child("$name").child("Deparment").setValue("$dep")
                    database.child("$name").child("Destination").setValue("$des")
                    database.child("$name").child("Purpose").setValue("$pur")
                    database.child("$name").child("NoofDays").setValue("$days")
                    database.child("$name").child("date").setValue("$date")
                    val intent= Intent(this,Submit::class.java)
                    intent.putExtra("name1","$name")
                    startActivity(intent)
                    finish()
                }
            }
            else{
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}