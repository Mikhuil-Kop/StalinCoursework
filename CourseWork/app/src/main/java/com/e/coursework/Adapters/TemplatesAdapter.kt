package com.e.coursework.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Activities.CarsListActivity
import com.e.coursework.Activities.TemplateActivity
import com.e.coursework.Cars.DATABASE
import com.e.coursework.R

class TemplatesAdapter (val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun getItemCount(): Int { return DATABASE.templates.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.recycler_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as ViewHolder

        view.valueView.text = DATABASE.templates[position].name
        view.valueView.setOnClickListener{
            val intent = Intent(context, TemplateActivity::class.java)
            intent.putExtra("TemplateIndex", position)
            context!!.startActivity(intent)
            notifyDataSetChanged()
        }
    }



    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val valueView = view.findViewById<TextView>(R.id.textView)
    }


}