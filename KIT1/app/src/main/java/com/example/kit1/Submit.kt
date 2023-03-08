package com.example.kit1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_submit.*
import java.text.SimpleDateFormat
import java.util.*

class Submit : AppCompatActivity() {
    private lateinit var year:EditText
    private lateinit var dep:EditText
    private lateinit var per:EditText
    private lateinit var t:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        supportActionBar?.hide()
        val name=findViewById<EditText>(R.id.namw21).text
        year=findViewById<EditText>(R.id.namw22)
        dep=findViewById<EditText>(R.id.namw23)
         per=findViewById<EditText>(R.id.namw25)
        val noper=findViewById<EditText>(R.id.namw24).text
        val button=findViewById<Button>(R.id.Submit)
        val add=findViewById<Button>(R.id.add)
        val database=Firebase.database.getReference("Faculty")
        val destination=intent.getStringExtra("Destina")
        val purpose=intent.getStringExtra("Purpose")
        val day=intent.getStringExtra("days")
        val fdate=intent.getStringExtra("Fdate")
        val name1=intent.getStringExtra("name1")
        val dep1=intent.getStringExtra("dep")
        val time=intent.getStringExtra("time")
        val leavetype=intent.getStringExtra("leavetype")
        val inform=intent.getStringExtra("inform")
        namw23.setOnClickListener{
            DeparmentSelection()
        }
        namw22.setOnClickListener{
            yearselection()
        }
        namw25.setOnClickListener{
            Periodselection()
        }
        val calendar = Calendar.getInstance()
        val simple = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = simple.format(calendar.time)
        val str=date.subSequence(14,19)

        button.setOnClickListener {
                if (name.isNotEmpty() && year.text.isNotEmpty() && dep.text.isNotEmpty() && per.text.isNotEmpty() && noper.isNotEmpty()) {

                    database.child("$dep1".toUpperCase()).child("$name1-$str").child("Name")
                        .setValue("$name1").addOnSuccessListener {
                            database.child("$dep1").child("$name1-$str").child("Deparment")
                                .setValue("$dep1".toUpperCase())
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("Destination")
                                .setValue("$destination")
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("Purpose")
                                .setValue("$purpose")
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("NoofDays")
                                .setValue("$day")
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("FromToDate")
                                .setValue("$fdate")
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("date")
                                .setValue("$date")
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("LeaveType")
                                .setValue("$leavetype")
                            database.child("$dep1".toUpperCase()).child("$name1-$str").child("Inform")
                                .setValue("$inform")
                            database.child("$dep1").child("$name1-$str").child("AName").setValue("$name")

                                database.child("$dep1").child("$name1-$str").child("AYear")
                                    .setValue("${year.text}")
                                database.child("$dep1").child("$name1-$str").child("Adeparment")
                                    .setValue("${dep.text}")
                                database.child("$dep1").child("$name1-$str").child("Anoofhours")
                                    .setValue("$noper")
                                database.child("$dep1").child("$name1-$str").child("Aperiods")
                                    .setValue("${per.text}")
                              database.child("$dep1").child("$name1-$str").child("time")
                                .setValue("$time")

                                val calendar = Calendar.getInstance()
                                val simple = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                                val date1 = simple.format(calendar.time)
                                val date=date1.subSequence(14,19)
                                if("$dep1".equals("${dep.text}")){
                                    val j=0;
                                }
                               else{

                                   database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Name")
                                            .setValue("$name1").addOnSuccessListener {
                                                database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Deparment")
                                                    .setValue("$dep1")
                                                database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Destination")
                                                    .setValue("$destination")
                                                database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Purpose")
                                                    .setValue("$purpose")
                                                database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("NoofDays")
                                                    .setValue("$day")
                                                database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("FromToDate")
                                                    .setValue("$fdate")
                                                database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("date")
                                                    .setValue("$date1")
                                               database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("LeaveType")
                                               .setValue("$leavetype")
                                               database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Inform")
                                               .setValue("$inform")
                                                database.child("${dep.text}").child("$name1-$date").child("AName").setValue("$name")
                                                database.child("${dep.text}").child("$name1-$date").child("AYear")
                                                    .setValue("${year.text}")
                                                database.child("${dep.text}").child("$name1-$date").child("Adeparment")
                                                    .setValue("${dep.text}")
                                                database.child("${dep.text}").child("$name1-$date").child("Anoofhours")
                                                    .setValue("$noper")
                                                database.child("${dep.text}").child("$name1-$date").child("Aperiods")
                                                    .setValue("${per.text}")
                                                database.child("${dep.text}").child("$name1-$date").child("time").setValue("$time")


                                    }
                                }


                                Toast.makeText(this, "Successfully Submitted", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(this, select::class.java)
                                startActivity(intent)
                                finish()
                            }



                }else {
                    Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
                }
            }
           add.setOnClickListener {
               if (name.isNotEmpty() && year.text.isNotEmpty() && dep.text.isNotEmpty() && per.text.isNotEmpty() && noper.isNotEmpty()) {

                   database.child("$dep1".toUpperCase()).child("$name1-$str").child("Name")
                       .setValue("$name1").addOnSuccessListener {
                           database.child("$dep1").child("$name1-$str").child("Deparment")
                               .setValue("$dep1".toUpperCase())
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("Destination")
                               .setValue("$destination")
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("Purpose")
                               .setValue("$purpose")
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("NoofDays")
                               .setValue("$day")
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("FromToDate")
                               .setValue("$fdate")
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("date")
                               .setValue("$date")
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("LeaveType")
                               .setValue("$leavetype")
                           database.child("$dep1".toUpperCase()).child("$name1-$str").child("Inform")
                               .setValue("$inform")
                           database.child("$dep1").child("$name1-$str").child("AName").setValue("$name")

                           database.child("$dep1").child("$name1-$str").child("AYear")
                               .setValue("${year.text}")
                           database.child("$dep1").child("$name1-$str").child("Adeparment")
                               .setValue("${dep.text}")
                           database.child("$dep1").child("$name1-$str").child("Anoofhours")
                               .setValue("$noper")
                           database.child("$dep1").child("$name1-$str").child("Aperiods")
                               .setValue("${per.text}")
                           database.child("$dep1").child("$name1-$str").child("time").setValue("$time")

                           val calendar = Calendar.getInstance()
                           val simple = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                           val date1 = simple.format(calendar.time)
                           val date=date1.subSequence(14,19)
                           if("$dep1".equals("${dep.text}")){
                               val j=0;
                           }
                           else{

                               database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Name")
                                   .setValue("$name1").addOnSuccessListener {
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Deparment")
                                           .setValue("$dep1")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Destination")
                                           .setValue("$destination")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Purpose")
                                           .setValue("$purpose")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("NoofDays")
                                           .setValue("$day")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("FromToDate")
                                           .setValue("$fdate")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("date")
                                           .setValue("$date1")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("LeaveType")
                                           .setValue("$leavetype")
                                       database.child("${dep.text}".toUpperCase()).child("$name1-$date").child("Inform")
                                           .setValue("$inform")
                                       database.child("${dep.text}").child("$name1-$date").child("AName").setValue("$name")
                                       database.child("${dep.text}").child("$name1-$date").child("AYear")
                                           .setValue("${year.text}")
                                       database.child("${dep.text}").child("$name1-$date").child("Adeparment")
                                           .setValue("${dep.text}")
                                       database.child("${dep.text}").child("$name1-$date").child("Anoofhours")
                                           .setValue("$noper")
                                       database.child("${dep.text}").child("$name1-$date").child("Aperiods")
                                           .setValue("${per.text}")
                                       database.child("${dep.text}").child("$name1-$date").child("time").setValue("$time")


                                   }
                           }


                           Toast.makeText(this, "Successfully Submitted", Toast.LENGTH_SHORT)
                               .show()
                           val intent = Intent(this, Submit2::class.java)
                           intent.putExtra("name1", "$name1")
                           intent.putExtra("Destina", "$destination")
                           intent.putExtra("Purpose", "$purpose")
                           intent.putExtra("days", "$day")
                           intent.putExtra("Fdate","$fdate")
                           intent.putExtra("leavetype","$leavetype")
                           intent.putExtra("inform","$inform")
                           intent.putExtra("dep", "$dep1".toUpperCase())
                           intent.putExtra("time","$time")
                           startActivity(intent)
                           finish()
                       }



               }else {
                   Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
               }

           }
        }
    private fun yearselection(){
        val listItem = arrayOf("I", "II", "III", "IV")
        val Mbuilder = AlertDialog.Builder(this@Submit)
        Mbuilder.setTitle("Choose a Year")
        Mbuilder.setSingleChoiceItems(listItem, -1) { dialogInterface, i ->

            year.setText(listItem[i])
            dialogInterface.dismiss()
        }
        Mbuilder.setNeutralButton("cancel") { dialog, which ->
            dialog.cancel()

        }
        val Mdailog = Mbuilder.create()
        Mdailog.show()
    }
    private fun Periodselection(){
        val listItem = arrayOf("I","II,","III","IV","V","VI","VII","VIII")
        val Mbuilder = AlertDialog.Builder(this@Submit)
        Mbuilder.setTitle("Choose a Period")
        Mbuilder.setSingleChoiceItems(listItem, -1) { dialogInterface, i ->
            t=per

            per.setText(t.text.append(","+listItem[i]))
            dialogInterface.dismiss()
        }
        Mbuilder.setNeutralButton("cancel") { dialog, which ->
            dialog.cancel()

        }
        val Mdailog = Mbuilder.create()
        Mdailog.show()
    }
    private fun DeparmentSelection(){
        val listItem=arrayOf("CSE","ECE","EEE","AERO","AGRI","MECH","BME","BTE","MCA")
        val Mbuilder=AlertDialog.Builder(this@Submit)
        Mbuilder.setTitle("Choose Department You are Going")
        Mbuilder.setSingleChoiceItems(listItem,-1){dailogInterface,i->
            dep.setText(listItem[i])
            dailogInterface.dismiss()

        }
        Mbuilder.setNeutralButton("cancel"){dailog,which->
            dailog.cancel()

        }
        val Mdailog=Mbuilder.create()
        Mdailog.show()
    }
    }

