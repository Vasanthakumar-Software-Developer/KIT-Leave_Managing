package com.example.kit1

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),DatePickerDialog.OnDateSetListener {
    private lateinit var fdate: EditText
    private lateinit var tdate: EditText
    private lateinit var checker: String
    private lateinit var from: String
    private lateinit var to: String
    private lateinit var time:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val l = resources.getStringArray(R.array.department)
        val arrayadapter = ArrayAdapter(this, R.layout.drobdown, l)
        val name = findViewById<EditText>(R.id.text1).text
        val des = findViewById<EditText>(R.id.text3).text
        val dep = findViewById<EditText>(R.id.text4)
        val days = findViewById<EditText>(R.id.text5).text
        val pur = findViewById<EditText>(R.id.text6).text
        fdate = findViewById<EditText>(R.id.button12)
        tdate = findViewById<EditText>(R.id.button13)
        val inform=findViewById<EditText>(R.id.inform)
        val leavetype=findViewById<EditText>(R.id.Leavetype)
        inform.setOnClickListener {
            val listItem = arrayOf("Letter", "Phone")
            val Mbuilder = AlertDialog.Builder(this@MainActivity)
            Mbuilder.setTitle("Select any Type")
            Mbuilder.setSingleChoiceItems(listItem, -1) { dialogInterface, i ->

                inform.setText(listItem[i])
                dialogInterface.dismiss()

            }
            Mbuilder.setNeutralButton("cancel") { dialog, which ->
                dialog.cancel()

            }
            val mDialog = Mbuilder.create()
            mDialog.show()

        }
        leavetype.setOnClickListener{
            val listItem = arrayOf("CL", "OD", "DL", "ML","LLP")
            val Mbuilder = AlertDialog.Builder(this@MainActivity)
            Mbuilder.setTitle("Choose any Type")
            Mbuilder.setSingleChoiceItems(listItem, -1) { dialogInterface, i ->

                leavetype.setText(listItem[i])
                dialogInterface.dismiss()

            }
            Mbuilder.setNeutralButton("cancel") { dialog, which ->
                dialog.cancel()

            }
            val mDialog = Mbuilder.create()
            mDialog.show()

        }
        text4.setOnClickListener {
            val listItem = arrayOf("CSE", "ECE", "EEE", "MECH", "AERO", "AGRI", "BME", "BTE", "MCA")
            val Mbuilder = AlertDialog.Builder(this@MainActivity)
            Mbuilder.setTitle("Choose a Department")
            Mbuilder.setSingleChoiceItems(listItem, -1) { dialogInterface, i ->

                dep.setText(listItem[i])
                dialogInterface.dismiss()

            }
            Mbuilder.setNeutralButton("cancel") { dialog, which ->
                dialog.cancel()

            }
            val mDialog = Mbuilder.create()
            mDialog.show()
        }
        fdate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            checker = "0"
            val datePickerDialog =
                DatePickerDialog(this@MainActivity, this@MainActivity, year, month, day)
            datePickerDialog.show()

        }
        tdate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            checker = "1"
            val datePickerDialog =
                DatePickerDialog(this@MainActivity, this@MainActivity, year,month,day)
            datePickerDialog.show()
        }


        val b = findViewById<Button>(R.id.Submit17)
//        val key=database.push().key!!

        val calendar = Calendar.getInstance()
        val simple = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val date = simple.format(calendar.time)
        val str=date.subSequence(17,19)


        b.setOnClickListener {
            if (name.isNotEmpty() && des.isNotEmpty() && dep.text.isNotEmpty() && days.isNotEmpty() && pur.isNotEmpty()) {


                        val intent = Intent(this,Submit::class.java)

                        intent.putExtra("name1", "$name")
                        intent.putExtra("Destina", "$des")
                        intent.putExtra("Purpose", "$pur")
                        intent.putExtra("days", "$days")
                        intent.putExtra("string", "$str")
                        intent.putExtra("Fdate","$from $to")
                        intent.putExtra("leavetype","${leavetype.text}")
                        intent.putExtra("inform","${inform.text}")
                        intent.putExtra("dep", "${dep.text}".toUpperCase())
                        intent.putExtra("time","$time")


                        startActivity(intent)
                        finish()

            } else {
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
         var month1=""
        if(month==0){
            month1="01"
        }
        if(month==1){
            month1="02"
        }
        if(month==2){
            month1="03"
        }
        if(month==3){
            month1="04"
        }
        if(month==4){
            month1="05"
        }
        if(month==5){
            month1="06"
        }
        if(month==6){
            month1="07"
        }
        if(month==7){
            month1="08"
        }
        if(month==8){
            month1="09"
        }
        if(month==9){
            month1="10"
        }
        if(month==10){
            month1="11"
        }
        if(month==11){
            month1="12"
        }
        if (checker.equals("0")) {

            fdate.setText("FROM:$dayOfMonth/$month1/$year")
            from = "FROM:$year/$month1/$dayOfMonth"
            time="$year/$month1/$dayOfMonth"
        } else {
            tdate.setText("TO:$dayOfMonth/$month1/$year")
            to = "TO:$year/$month1/$dayOfMonth"

        }
    }



}
