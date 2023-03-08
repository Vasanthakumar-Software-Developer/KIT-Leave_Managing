package com.example.kit1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.database.*
import com.google.firebase.database.ktx.snapshots
import org.w3c.dom.Text

class Pin : AppCompatActivity() {
 private lateinit var D:DatabaseReference
 private lateinit var chil:EditText
 public lateinit var department:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        supportActionBar?.hide()
        val a = findViewById<EditText>(R.id.editTextTextPassword).text
        val b = findViewById<Button>(R.id.button8)
       var c=0
        D = FirebaseDatabase.getInstance().getReference("password")
        b.setOnClickListener {
            c=0
            D.child("pass").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {

                    val intent = Intent(this, detail::class.java)

                    intent.putExtra("passcheck","CSE")
                    intent.putExtra("pinchange", "pass")
                    startActivity(intent)

                    finish()
                }
                else{
                   c=1
                }
            }

            D.child("pass1").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck","ECE")
                    intent.putExtra("pinchang", "pass1")
                    startActivity(intent)

                    finish()
                }
                else{
                    c=1
                }
            }
            D.child("pass2").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck","EEE")
                    intent.putExtra("pinchang", "pass2")
                    startActivity(intent)

                    finish()
                }else{
                   c=1
                }

            }
            D.child("pass3").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck","MECH")
                    intent.putExtra("pinchang", "pass3")
                    startActivity(intent)

                    finish()
                }else{
                    c=1
                }
            }
            D.child("pass4").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck","BME")
                    intent.putExtra("pinchang", "pass4")
                    startActivity(intent)

                    finish()
                }else{
                    c=1
                }
            }
            D.child("pass5").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck", "AGRI")
                    intent.putExtra("pinchang", "pass5")
                    startActivity(intent)

                    finish()
                }else{
                         c=1
                }
            }
            D.child("pass6").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck", "AERO")
                    intent.putExtra("pinchang", "pass6")
                    startActivity(intent)

                    finish()
                }else{
                     c=1
                }
            }
            D.child("pass7").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck", "BTE")
                    intent.putExtra("pinchang", "pass7")
                    startActivity(intent)

                    finish()
                }else{
                    c=1
                }
            }
            D.child("pass8").get().addOnSuccessListener {
                if ("$a" == "${it.value}") {
                    Toast.makeText(this, "${it.value}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, detail::class.java)
                    intent.putExtra("passcheck", "MCA")
                    intent.putExtra("pinchang", "pass8")
                    startActivity(intent)

                    finish()
                }
                if(c==1){
                    Toast.makeText(this,"Enter Valid PIN",Toast.LENGTH_SHORT).show()
                }
            }
        }





    }
}







