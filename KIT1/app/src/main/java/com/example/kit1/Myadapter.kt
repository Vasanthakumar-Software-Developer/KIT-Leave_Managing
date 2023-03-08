package com.example.kit1

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.util.ArrayUtils.contains
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Myadapter(private val userlist:ArrayList<User>): RecyclerView.Adapter<Myadapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.useritem,
        parent,false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cuurentitem=userlist[position]
        val calendar = Calendar.getInstance()
        val simple = SimpleDateFormat("yyyy/MM/dd")
        val date1 = simple.format(calendar.time)
            holder.name.text = cuurentitem.Name
            holder.des.text = cuurentitem.Destination
            holder.dep.text = cuurentitem.Deparment
            holder.pur.text = cuurentitem.Purpose
            holder.day.text = cuurentitem.NoofDays
            holder.Aname.text = cuurentitem.AName
            holder.Aye.text = cuurentitem.AYear
            holder.Adep.text = cuurentitem.Adeparment
            holder.Adays.text = cuurentitem.Anoofhours
            holder.Aper.text = cuurentitem.Aperiods
            holder.Fromto.text = cuurentitem.FromToDate
            holder.Date.text = cuurentitem.date
            holder.Leavetype.text = cuurentitem.LeaveType
            holder.Inform.text = cuurentitem.Inform
            if ("${cuurentitem.date}".substring(0, 10).equals(date1)) {
                  holder.mention.text = "                              NEW                       "
           }

            }
    override fun getItemCount(): Int {
        return userlist.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mention:TextView=itemView.findViewById(R.id.mention)
        val name:TextView=itemView.findViewById(R.id.UserName)
        val des:TextView=itemView.findViewById(R.id.Desti)
        val dep:TextView=itemView.findViewById(R.id.Depname)
        val pur:TextView=itemView.findViewById(R.id.Purpose)
        val day:TextView=itemView.findViewById(R.id.Days)
        val Aname:TextView=itemView.findViewById(R.id.UserName1)
        val Aye:TextView=itemView.findViewById(R.id.Desti1)
        val Adep:TextView=itemView.findViewById(R.id.Depname1)
        val Adays:TextView=itemView.findViewById(R.id.Days1)
        val Aper:TextView=itemView.findViewById(R.id.Purpose1)
        val Fromto:TextView=itemView.findViewById(R.id.FromToDate)
        val Date:TextView=itemView.findViewById(R.id.Date)
        val Leavetype:TextView=itemView.findViewById(R.id.Leavetype)
        val Inform:TextView=itemView.findViewById(R.id.Inform)

    }
}