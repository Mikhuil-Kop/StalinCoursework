package com.e.coursework.Activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Adapters.CarParamAdapter
import com.e.coursework.Cars.Car
import com.e.coursework.Cars.DATABASE
import com.e.coursework.R


class CarActivity: AppCompatActivity() {
    lateinit var car : Car
    lateinit var list : RecyclerView
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.car_activity)

        val index = intent.extras!!.getInt("CarIndex")
        car = DATABASE.cars[index]

        //Подключение списка
        list = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.setHasFixedSize(true)

        val adapter = CarParamAdapter(this, car, index)
        list.adapter = adapter


        //spinner
        spinner = findViewById(R.id.spinner)
        for (i in 0 until  DATABASE.templates.size)
            if(DATABASE.templates[i] == car.template){
                spinner.setSelection(i)
                break
            }


        // Настраиваем адаптер
        val adapter2 = ArrayAdapter<String>(this, com.e.coursework.R.layout.simple_dropdown_item_1line, Array<String>(
            DATABASE.templates.size){ i -> DATABASE.templates[i].name})

        // Вызываем адаптер
        spinner.adapter = adapter2

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long) {

                car = Car(DATABASE.templates[selectedItemPosition])
                DATABASE.cars[index] = car
                list.adapter!!.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

}