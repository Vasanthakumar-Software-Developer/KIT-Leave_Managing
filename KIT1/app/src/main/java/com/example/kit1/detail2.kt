package com.example.kit1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*

class detail2 : AppCompatActivity(),DatePickerDialog.OnDateSetListener {
    private lateinit var fromdate:EditText
    private lateinit var todate:EditText
    private lateinit var checker:String
    private  var from:String=""
    private  var to:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        val facname=findViewById<EditText>(R.id.facname).text
         fromdate=findViewById<EditText>(R.id.fromdate)
        todate=findViewById<EditText>(R.id.todate)
        val check=findViewById<Button>(R.id.check)
        val pinchange=intent.getStringExtra("pinchange")
        val passcheck=intent.getStringExtra("passcheck")
        fromdate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            checker = "0"
            val datePickerDialog =
                DatePickerDialog(this@detail2, this@detail2, year, month, day)
            datePickerDialog.show()

        }
        todate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            checker = "1"
            val datePickerDialog =
                DatePickerDialog(this@detail2, this@detail2, year,month,day)
            datePickerDialog.show()
        }

        check.setOnClickListener {
            val intent= Intent(this,detail::class.java)
            intent.putExtra("passcheck","$passcheck")
            intent.putExtra("facname","$facname")
            intent.putExtra("pinchange","$pinchange")
            if(from=="" && to=="") {
                intent.putExtra("fromdate", "$from")
                intent.putExtra("todate", "$to")
            }
            startActivity(intent)
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

            fromdate.setText("FROM:$dayOfMonth/$month1/$year")
            from = "$year/$month1/$dayOfMonth"

        } else {
            todate.setText("TO:$dayOfMonth/$month1/$year")
            to = "$year/$month1/$dayOfMonth"
        }
    }
}
