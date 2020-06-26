package com.e.coursework.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.e.coursework.Cars.DATABASE
import com.e.coursework.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        DATABASE.LoadFromFile(this)
    }

    fun ToCarsList(view: View?) {
        val intent = Intent(this, CarsListActivity::class.java)
        startActivity(intent)
    }

    fun ToTemplatesList(view: View?){
        val intent = Intent(this, TemplatesListActivity::class.java)
        startActivity(intent)
    }

    fun ToPartsList(view: View?){
        val intent = Intent(this, PartsActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        DATABASE.SaveToFile(this)
    }
}
