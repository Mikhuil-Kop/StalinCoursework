package com.e.coursework.Activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Adapters.TemplateParamAdapter
import com.e.coursework.Cars.*
import com.e.coursework.R

class TemplateActivity : AppCompatActivity() {
    lateinit var template : Template
    lateinit var list : RecyclerView
    lateinit var text : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.template_activity)
        template = DATABASE.templates[intent.extras!!.getInt("TemplateIndex")]

        text =  findViewById(R.id.editText)
        text.setText(template.name);

        //Подключение списка
        list = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.setHasFixedSize(true)


        val adapter = TemplateParamAdapter(this, template)
        list.adapter = adapter
    }

    fun AddButton(view: View?) {
        template.list.add(Template.TemplateParam("Новый параметр", 'f'))
        list.adapter!!.notifyDataSetChanged()
    }

    fun DelButton(view: View?) {
        if(template.list.size != 0) {
            template.list.removeAt(template.list.size - 1)
            list.adapter!!.notifyDataSetChanged()
        }
    }

    override fun onStop() {
        super.onStop()
        template.name = text.text.toString()
    }

}