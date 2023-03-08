package com.example.kit1


import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.api.AnnotationsProto.http
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.itextpdf.text.Element
import com.itextpdf.text.Image
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfImage
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PdfApplication : AppCompatActivity() {
    private lateinit var pdfData:EditText
    private lateinit var btn:Button
    private lateinit var n:String
    private lateinit var de:String
    private lateinit var d:String
    private lateinit var p:String
    private lateinit var no: String
    private lateinit var An:String
    private lateinit var Ade:String
    private lateinit var Ad:String
    private lateinit var Ap:String
    private lateinit var Ano:String
    private lateinit var FT:String
    private lateinit var LT:String
    private lateinit var IT:String
    private lateinit var mfilepath:String
    private var STORAGE=1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_application)
        val passcheck=intent.getStringExtra("passcheck1")

        pdfData=findViewById(R.id.editTextTextPersonName5)
        var name1:String=""
        Toast.makeText(this,"$passcheck",Toast.LENGTH_SHORT).show()
        var listItem= arrayOf<String>()
        if(name1.isEmpty()) {

            val ref = FirebaseDatabase.getInstance().getReference("Faculty").child("$passcheck")
            ref.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (usersnapshot in snapshot.children) {
                            name1 += usersnapshot.key.toString() + " "


                        }


                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
            pdfData.setOnClickListener {

                listItem = name1.split(" ").toTypedArray()
//            var k=0
//            Toast.makeText(this,"$name1",Toast.LENGTH_SHORT).show()
//            for(i in arr){
//                if(k<arr.size) {
//                    Toast.makeText(this,"$i",Toast.LENGTH_SHORT).show()
//                    listItem.set(k,"$i")
//                    k=k+1
//                }
//            }
                Toast.makeText(this, "$name1", Toast.LENGTH_SHORT).show()
                val Mbuilder = AlertDialog.Builder(this@PdfApplication)
                Mbuilder.setTitle("Choose a Department")
                Mbuilder.setSingleChoiceItems(listItem, -1) { dialogInterface, i ->

                    pdfData.setText(listItem[i])
                    dialogInterface.dismiss()

                }
                Mbuilder.setNeutralButton("cancel") { dialog, which ->
                    dialog.cancel()

                }
                val mDialog = Mbuilder.create()
                mDialog.show()
            }

        btn=findViewById<Button>(R.id.button11)

        btn.setOnClickListener{
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permission, STORAGE)

                } else {
                    save()
                }
            }
            else{
                save()
            }
            }
        }
    private fun save(){

        val data=pdfData.text
        val mDoc=com.itextpdf.text.Document()
        val passcheck=intent.getStringExtra("passcheck1")
        Toast.makeText(this,"$passcheck",Toast.LENGTH_SHORT).show()
        val dp=FirebaseDatabase.getInstance().getReference("Faculty")
        val mifle=SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(System.currentTimeMillis())
        val mFilename=data
        mfilepath=Environment.getExternalStorageDirectory().toString()+"/"+mFilename+".pdf"
        val writer:PdfWriter= PdfWriter.getInstance(mDoc,FileOutputStream(mfilepath))


        mDoc.addAuthor("Vasanth")
        dp.child("$passcheck").child("$data").child("Name").get().addOnSuccessListener {
             n="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Destination").get().addOnSuccessListener {
            de="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Deparment").get().addOnSuccessListener {
            d="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Purpose").get().addOnSuccessListener {
            p="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("NoofDays").get().addOnSuccessListener {
            no="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("AName").get().addOnSuccessListener {
            An="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("AYear").get().addOnSuccessListener {
            Ade="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Adeparment").get().addOnSuccessListener {
            Ad="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Anoofhours").get().addOnSuccessListener {
            Ap="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Aperiods").get().addOnSuccessListener {
            Ano="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("FromToDate").get().addOnSuccessListener {
            FT="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("LeaveType").get().addOnSuccessListener {
            LT="${it.value}"
        }
        dp.child("$passcheck").child("$data").child("Inform").get().addOnSuccessListener {
            IT="${it.value}"
        }

        dp.child("$passcheck").child("$data").get().addOnSuccessListener {

            try {
                mDoc.open()

                var table = PdfPTable(2)
                var table2=PdfPTable(2)
                val c1=PdfPCell(Phrase("NAME"))
                val c2=PdfPCell(Phrase("$n".toUpperCase()))
                val c3=PdfPCell(Phrase("DESIGNATION"))
                val c4=PdfPCell(Phrase("$de".toUpperCase()))
                val c5=PdfPCell(Phrase("DEPARTMENT"))
                val c6=PdfPCell(Phrase("$d".toUpperCase()))
                val c7=PdfPCell(Phrase("PURPOSE"))
                val c8=PdfPCell(Phrase("$p".toUpperCase()))
                val c9=PdfPCell(Phrase("NO OF DAYS"))
                val c10=PdfPCell(Phrase("$no".toUpperCase()))
                val c11=PdfPCell(Phrase("LEAVE DATE"))
                val c12=PdfPCell(Phrase("$FT"))
                val c13=PdfPCell(Phrase("LEAVE TYPE"))
                val c14=PdfPCell(Phrase("$LT"))
                val c15=PdfPCell(Phrase("INFORMED THROUGH"))
                val c16=PdfPCell(Phrase("$IT"))




                table.addCell(c1)
                table.addCell(c2)
                table.addCell(c3)
                table.addCell(c4)
                table.addCell(c5)
                table.addCell(c6)
                table.addCell(c7)
                table.addCell(c8)
                table.addCell(c9)
                table.addCell(c10)
                table.addCell(c11)
                table.addCell(c12)
                table.addCell(c13)
                table.addCell(c14)
                table.addCell(c15)
                table.addCell(c16)



                mDoc.add(Paragraph("\t\t\t\t\t\t\t\t\t\t\t\t\t\t                     KIT-KALAINARKARUNANIDHI INSTITUTE OF TECHNOLOGY\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t                                        (AN AUTONOMOUS INSTITUTION)\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t                                       COIMBATORE, TAMILNADU-641402\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t                                        Affiliated to Anna University, Chennai\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t        Accredited by NAAC with ‘A’ Grade | Accredited by NBA (CSE, ECE, EEE & MECH)\n\n\n"))
                mDoc.add(Paragraph("                                                    FACULTY LEAVE DETAILS\n\n"))
                mDoc.add(table)
                mDoc.add(Paragraph("\n\n                                                  ALTERNATE FACULTY DETAILS\n\n"))
                val ct1=PdfPCell(Phrase("NAME"))
                val ct2=PdfPCell(Phrase("$An".toUpperCase()))
                val ct3=PdfPCell(Phrase("DESIGNATION"))
                val ct4=PdfPCell(Phrase("$Ade".toUpperCase()))
                val ct5=PdfPCell(Phrase("DEPARTMENT"))
                val ct6=PdfPCell(Phrase("$Ad".toUpperCase()))
                val ct7=PdfPCell(Phrase("NO OF HOURS"))
                val ct8=PdfPCell(Phrase("$Ap".toUpperCase()))
                val ct9=PdfPCell(Phrase(("CLASS HOURS")))
                val ct10=PdfPCell(Phrase("$Ano".toUpperCase()))
                table2.addCell(ct1)
                table2.addCell(ct2)
                table2.addCell(ct3)
                table2.addCell(ct4)
                table2.addCell(ct5)
                table2.addCell(ct6)
                table2.addCell(ct7)
                table2.addCell(ct8)
                table2.addCell(ct9)
                table2.addCell(ct10)
                mDoc.add(table2)
                mDoc.add(Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n $mifle                                                                                                                          KIT"))

                mDoc.close()

                Toast.makeText(this, "Pdf downloaded succesfull $mfilepath", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "PdfError", Toast.LENGTH_SHORT).show()

            }
        }
    }



    fun checkPermissions(): Boolean {
        var writeStoragePermission = ContextCompat.checkSelfPermission(
            applicationContext,
            WRITE_EXTERNAL_STORAGE
        )

        // on below line we are creating a variable
        // for reading external storage permission
        var readStoragePermission = ContextCompat.checkSelfPermission(
            applicationContext,
            READ_EXTERNAL_STORAGE
        )

        // on below line we are returning true if both the
        // permissions are granted and returning false
        // if permissions are not granted.
        return writeStoragePermission == PackageManager.PERMISSION_GRANTED
                && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    // on below line we are creating a function to request permission.
    fun requestPermission() {

        // on below line we are requesting read and write to
        // storage permission for our application.
        ActivityCompat.requestPermissions(
            this,
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), STORAGE
        )
    }

    // on below line we are calling
    // on request permission result.

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            STORAGE->{
                if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    save()
                }
                else
                {
                    Toast.makeText(this,"Denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    }



