package com.example.kit1

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_detail2.*
import org.w3c.dom.Text

class detail : AppCompatActivity() {
     private lateinit var dpref:DatabaseReference
     private lateinit var useRecyler:RecyclerView
     private lateinit var userArrayList: ArrayList<User>
     lateinit var toggle: ActionBarDrawerToggle
     private var facname:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        facname=intent.getStringExtra("facname").toString()
        Toast.makeText(this,"$facname - h$fromdate j$todate",Toast.LENGTH_SHORT).show()
        val pinchange=intent.getStringExtra("pinchange")
        val passcheck=intent.getStringExtra("passcheck")
        val fromdate=intent.getStringExtra("fromdate")
        val todate=intent.getStringExtra("todate")
        val drawerLayout: DrawerLayout = findViewById(R.id.draw)
        val navView: NavigationView = findViewById(R.id.nav)
//        val swipe=findViewById<SwipeRefreshLayout>(R.id.refresh)
//        swipe.setOnRefreshListener {
//            getUserData()
//            Toast.makeText(this,"Refreshed",Toast.LENGTH_SHORT).show()
//            swipe.isRefreshing=false
//        }

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val k=Intent(this,Changepin::class.java)
        k.putExtra("pinchange1","$pinchange")
        val intent=Intent(this,PdfApplication::class.java)
        intent.putExtra("passcheck1","$passcheck")

        val A=Intent(this,RemoveApplic::class.java)
        A.putExtra("passcheck2","$passcheck")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.DPdf->startActivity(intent)
                R.id.Cpin->startActivity(k)
                R.id.Dapp->startActivity(A)

            }
            true

        }
        val filter=findViewById<Button>(R.id.filter)
        filter.setOnClickListener {
            val intent1=Intent(this,detail2::class.java)
            intent1.putExtra("passcheck","$passcheck")
            intent1.putExtra("pinchange","$pinchange")
            startActivity(intent1)
            finish()

        }
        useRecyler=findViewById(R.id.userlist)
        useRecyler.layoutManager=LinearLayoutManager(this)
        useRecyler.setHasFixedSize(true)
        userArrayList= arrayListOf<User>()

        getUserData()


    }



    private fun getUserData(){
        val passcheck=intent.getStringExtra("passcheck")
        val pinchange=intent.getStringExtra("pinchange")
        val fromdate=intent.getStringExtra("fromdate")
        val todate=intent.getStringExtra("todate")
        val ref=FirebaseDatabase.getInstance().getReference("Faculty").child("$passcheck")

        if("$facname"=="null") {
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (usersnapshot in snapshot.children) {
                            val user = usersnapshot.getValue(User::class.java)
                            userArrayList.add(user!!)
                            userArrayList.sortedByDescending { it.date }
                            useRecyler.adapter = Myadapter(userArrayList)
                        }

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
        else{
            if("$fromdate"=="null" && "$todate"=="null") {
                ref.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (usersnapshot in snapshot.children) {
                                if ("$facname" == usersnapshot.child("Name").value.toString()) {
                                    val user = usersnapshot.getValue(User::class.java)
                                    userArrayList.add(user!!)
                                    userArrayList.sortedByDescending { it.date }
                                    useRecyler.adapter = Myadapter(userArrayList)
                                }
                            }
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            }
            else{

                Toast.makeText(this,"sorry",Toast.LENGTH_SHORT).show()
                ref.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            for (usersnapshot in snapshot.children) {

                                if ("$facname" == usersnapshot.child("Name").value.toString() && ("$fromdate"<=usersnapshot.child("FromToDate").value.toString().substring(5,14) && "$todate">=usersnapshot.child("FromToDate").value.toString().substring(18,27))) {
                                    val user = usersnapshot.getValue(User::class.java)
                                    userArrayList.add(user!!)
                                    userArrayList.sortedByDescending { it.date }
                                    useRecyler.adapter = Myadapter(userArrayList)
                                }
                            }
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

}