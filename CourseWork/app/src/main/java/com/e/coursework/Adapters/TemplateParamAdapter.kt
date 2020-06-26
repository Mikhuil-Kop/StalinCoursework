package com.e.coursework.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Cars.Template
import com.e.coursework.R

class TemplateParamAdapter (context: Context?, val template: Template) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int { return template.list.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = inflater.inflate(R.layout.recycler_template_param, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as ViewHolder

        view.valueView.setText(template.list[position].key)
        if(template.list[position].type == 'i')
            view.spinner.setSelection(0)
        else if(template.list[position].type == 's')
            view.spinner.setSelection(1)


        view.button.setOnClickListener {
            template.list[position].key = view.valueView.text.toString()
            if(view.spinner.selectedItemId == 0.toLong())
                template.list[position].type = 'i'
            else
                template.list[position].type = 's'
        }
    }



    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        val valueView = view.findViewById<EditText>(R.id.editText)
        val button = view.findViewById<Button>(R.id.saveButton)
        val spinner = view.findViewById<Spinner>(R.id.spinner)
    }


}