package com.e.coursework.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Adapters.CarsAdapter
import com.e.coursework.Adapters.TemplatesAdapter
import com.e.coursework.Cars.Car
import com.e.coursework.Cars.DATABASE
import com.e.coursework.Cars.Database
import com.e.coursework.Cars.Template
import com.e.coursework.R

class CarsListActivity: BigListActivity() {
    lateinit var list : RecyclerView
    lateinit var text : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.big_list_activity)

        text = findViewById(R.id.editText)

        //Подключение списка
        list = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.setHasFixedSize(true)

        val adapter = CarsAdapter(this)
        list.adapter = adapter
    }

    override fun AddButton(view: View?) {
        if(DATABASE.templates.size < 1)
            return

        DATABASE.cars.add(Car(DATABASE.templates[0]))
        //val intent = Intent(this, CarActivity::class.java)
        //intent.putExtra("CarsIndex", DATABASE.cars.size - 1)
        //startActivity(intent)
        Log.d("++++++", DATABASE.cars.size.toString())
        list.adapter!!.notifyDataSetChanged()
    }

    override fun DelButton(view: View?) {
        if (DATABASE.cars.size != 0) {
            DATABASE.cars.removeAt(DATABASE.cars.size - 1)
            list.adapter!!.notifyDataSetChanged()
        }
    }

    override fun FindButton(view: View?) {
        super.FindButton(view)

        val newlist = DATABASE.Quicksort(DATABASE.cars, text.text.toString())
        for(i in 0 until newlist.size)
            DATABASE.cars[i] = newlist[i]

        list.adapter!!.notifyDataSetChanged()
    }
}
