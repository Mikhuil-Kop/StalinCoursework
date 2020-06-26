package com.e.coursework.Activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.coursework.Adapters.ParamsAdapter
import com.e.coursework.Cars.DATABASE
import com.e.coursework.R

class PartsActivity: BigListActivity() {
    lateinit var list : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        //Подключение списка
        list = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.setHasFixedSize(true)


        val adapter = ParamsAdapter(this)
        list.adapter = adapter
    }

    override fun AddButton(view: View?) {
        DATABASE.params.add("Новый элемент")
        list.adapter!!.notifyDataSetChanged()
    }

    override fun DelButton(view: View?) {
        if(DATABASE.params.size != 0) {
            DATABASE.params.removeAt(DATABASE.params.size - 1)
            list.adapter!!.notifyDataSetChanged()
        }
    }

}