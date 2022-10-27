package com.example.kit1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_detail.view.*
import org.w3c.dom.Text

class detail : AppCompatActivity() {
     private lateinit var dpref:DatabaseReference
     private lateinit var useRecyler:RecyclerView
     private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
        useRecyler=findViewById(R.id.userlist)
        useRecyler.layoutManager=LinearLayoutManager(this)
        useRecyler.setHasFixedSize(true)
        userArrayList= arrayListOf<User>()
        getUserData()


    }
    private fun getUserData(){
        dpref=FirebaseDatabase.getInstance().getReference("Faculty")
        val ref=FirebaseDatabase.getInstance().getReference("Faculty").orderByChild("date")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(usersnapshot in snapshot.children){
                        val user=usersnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }
                    userArrayList.sortByDescending{ it.date}
                    useRecyler.adapter=Myadapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}