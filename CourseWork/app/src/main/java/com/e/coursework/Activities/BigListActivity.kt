package com.e.coursework.Activities

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BigListActivity : AppCompatActivity() {

    open fun AddButton(view: View?) {}

    open fun DelButton(view: View?) {}

    open fun FindButton(view: View?) {}
}