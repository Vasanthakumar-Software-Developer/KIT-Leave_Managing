package com.example.kit1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class Myadapter(private val userlist:ArrayList<User>): RecyclerView.Adapter<Myadapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.useritem,
        parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cuurentitem=userlist[position]
        holder.name.text=cuurentitem.Name
        holder.des.text=cuurentitem.Destination
        holder.dep.text=cuurentitem.Deparment
        holder.pur.text=cuurentitem.Purpose
        holder.day.text=cuurentitem.NoofDays
        holder.Aname.text=cuurentitem.AName
        holder.Aye.text=cuurentitem.AYear
        holder.Adep.text=cuurentitem.Adeparment
        holder.Adays.text=cuurentitem.Anoofhours
        holder.Aper.text=cuurentitem.Aperiods
        holder.Date.text=cuurentitem.date
    }
    override fun getItemCount(): Int {
        return userlist.size
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
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
        val Date:TextView=itemView.findViewById(R.id.Date)

    }
}