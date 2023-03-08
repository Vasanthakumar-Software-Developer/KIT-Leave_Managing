package com.example.kit1

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.database.FirebaseDatabase
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.getInstance

class MainActivity2 : AppCompatActivity() {

    // on below line we are creating
    // a variable for our image view.
    private lateinit var pdfData: EditText
    private lateinit var btn:Button
    private lateinit var n:EditText
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
    //
    lateinit var generatePDFBtn: Button

    // declaring width and height
    // for our PDF file.
    var pageHeight = 1120
    var pageWidth = 792

    // creating a bitmap variable
    // for storing our images
    lateinit var bmp: Bitmap
    lateinit var scaledbmp: Bitmap

    // on below line we are creating a
    // constant code for runtime permissions.
    var PERMISSION_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // on below line we are initializing our button with its id.
        generatePDFBtn = findViewById<Button>(R.id.genpdf)

        // on below line we are initializing our bitmap and scaled bitmap.
        bmp = BitmapFactory.decodeResource(resources, R.drawable.ellipse_2)
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false)

        // on below line we are checking permission
        if (checkPermissions()) {
            // if permission is granted we are displaying a toast message.
        } else {
            // if the permission is not granted
            // we are calling request permission method.
            requestPermission()
        }

        // on below line we are adding on click listener for our generate button.
        Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show()
        generatePDFBtn.setOnClickListener {
            Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show()
            generatePDF()
        }
    }

    // on below line we are creating a generate PDF method
    // which is use to generate our PDF file.
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun generatePDF() {
        // creating an object variable
        // for our PDF document.

        var pdfDocument: PdfDocument = PdfDocument()
        val data=findViewById<EditText>(R.id.pdf)
        val file: File = File(Environment.getExternalStorageDirectory(), "${data.text}.pdf")



        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        var paint: Paint = Paint()
        var title: Paint = Paint()

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        var myPageInfo: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

        // below line is used for setting
        // start page for our PDF file.
        var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)




        // creating a variable for canvas
        // from our page of PDF.
        var canvas: Canvas = myPage.canvas
        Toast.makeText(this,"hi1",Toast.LENGTH_SHORT).show()

try{
        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.


    canvas.drawBitmap(scaledbmp, 56F, 40F, paint)

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))

        title.textSize = 15F

        title.setColor(ContextCompat.getColor(this, R.color.black))


        canvas.drawText("             KIT-KALAINARKARUNANIDHI INSTITUTE OF TECHNOLOGY", 209F, 80F, title)
        canvas.drawText("                              (AN AUTONOMOUS INSTITUTION)", 209F, 100F, title)
        canvas.drawText("                              COIMBATORE, TAMILNADU-641402", 209F, 120F, title)
        canvas.drawText("                          Affiliated to Anna University, Chennai", 209F, 140F, title)
        canvas.drawText("Accredited by NAAC with ‘A’ Grade | Accredited by NBA (CSE, ECE, EEE & MECH)", 209F, 160F, title)
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
        title.setColor(ContextCompat.getColor(this, R.color.black))
        title.textSize = 15F

        title.textAlign = Paint.Align.CENTER
    Toast.makeText(this,"hi2",Toast.LENGTH_SHORT).show()




        pdfDocument.finishPage(myPage)

        // below line is used to set the name of
        // our PDF file and its path.




            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(applicationContext, "PDF file generated..", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            // below line is used
            // to handle error
            e.printStackTrace()

            // on below line we are displaying a toast message as fail to generate PDF
            Toast.makeText(applicationContext, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
                .show()
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close()
    }

    fun checkPermissions(): Boolean {
        // on below line we are creating a variable for both of our permissions.

        // on below line we are creating a variable for
        // writing to external storage permission
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
            arrayOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), PERMISSION_CODE
        )
    }

    // on below line we are calling
    // on request permission result.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // on below line we are checking if the
        // request code is equal to permission code.
        if (requestCode == PERMISSION_CODE) {

            // on below line we are checking if result size is > 0
            if (grantResults.size > 0) {

                // on below line we are checking
                // if both the permissions are granted.
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1]
                    == PackageManager.PERMISSION_GRANTED) {

                    // if permissions are granted we are displaying a toast message.
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()

                } else {

                    // if permissions are not granted we are
                    // displaying a toast message as permission denied.
                    Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }




}

