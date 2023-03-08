package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class RemoveApplic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_applic)
         val dp=FirebaseDatabase.getInstance().getReference("Faculty")
         val d=findViewById<EditText>(R.id.fd).text
        val btn=findViewById<Button>(R.id.button10)



        btn.setOnClickListener{
            val passcheck=intent.getStringExtra("passcheck2")
            Toast.makeText(this,"$passcheck details deleted succesfully",Toast.LENGTH_SHORT).show()

            dp.child("$passcheck").child("$d").removeValue().addOnSuccessListener {
                Toast.makeText(this,"$d details deleted succesfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"$d Enter valid data!!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}