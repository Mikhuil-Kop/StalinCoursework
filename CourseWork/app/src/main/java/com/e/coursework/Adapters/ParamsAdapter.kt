package com.e.coursework.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.e.coursework.Cars.DATABASE
import com.e.coursework.Cars.Database
import com.e.coursework.R


class ParamsAdapter(context: Context?) : RecyclerView.Adapter<ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int { return DATABASE.params.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.recycler_param, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as ViewHolder

        view.valueView.text = DATABASE.params[position]
        view.button.setOnClickListener {
            DATABASE.params[position] = view.valueView.text.toString();
        }
    }



    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val valueView = view.findViewById<TextView>(R.id.editText)
        val button = view.findViewById<Button>(R.id.saveButton)
    }


}