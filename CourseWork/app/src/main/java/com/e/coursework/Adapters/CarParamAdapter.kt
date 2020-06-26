package com.e.coursework.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Cars.Car
import com.e.coursework.Cars.DATABASE
import com.e.coursework.Cars.FloatParam
import com.e.coursework.Cars.StringParam
import com.e.coursework.R


class CarParamAdapter(val context: Context?, val car: Car, val index :Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int { return car.params.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.recycler_car_param, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as ViewHolder

        view.text.text = car.params[position].key
        view.valueView.setText(car.params[position].absValue().toString())

        val adapter = ArrayAdapter<String>(context!!, R.layout.simple_dropdown_item_1line, DATABASE.params)
        view.valueView.setAdapter(adapter)

        view.button.setOnClickListener {
            if(car.params[position] is StringParam)
                (car.params[position] as  StringParam).value = view.valueView.text.toString()
            else if(car.params[position] is FloatParam)
                (car.params[position] as  FloatParam).value  = view.valueView.text.toString().toFloat()
        }
    }



    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.findViewById<TextView>(R.id.textView)
        val valueView = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val button = view.findViewById<Button>(R.id.button)
    }


}