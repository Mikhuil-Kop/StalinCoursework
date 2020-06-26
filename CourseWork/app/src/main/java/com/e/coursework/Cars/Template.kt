package com.e.coursework.Cars

class Template {
    var name = "Новый шаблон"
    var list = mutableListOf(TemplateParam("Название", 's'))

    //f - Float, s - String
    data class TemplateParam(var key : String, var type: Char)
}