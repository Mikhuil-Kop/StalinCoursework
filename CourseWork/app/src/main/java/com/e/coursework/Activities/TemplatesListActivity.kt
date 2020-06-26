package com.e.coursework.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Adapters.ParamsAdapter
import com.e.coursework.Adapters.TemplatesAdapter
import com.e.coursework.Cars.DATABASE
import com.e.coursework.Cars.Template
import com.e.coursework.R

class TemplatesListActivity : BigListActivity() {
    lateinit var list : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        //Подключение списка
        list = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.setHasFixedSize(true)


        val adapter = TemplatesAdapter(this)
        list.adapter = adapter
    }

    override fun AddButton(view: View?) {
        DATABASE.templates.add(Template())
        list.adapter!!.notifyDataSetChanged()
    }

    override fun DelButton(view: View?) {
        if(DATABASE.templates.size != 0) {
            DATABASE.templates.removeAt(DATABASE.templates.size - 1)
            list.adapter!!.notifyDataSetChanged()
        }
    }

}