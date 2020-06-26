package com.e.coursework.Cars

import android.util.Log

class Car {
    var template : Template? = null
    var params = mutableListOf<Param>()

    constructor(template :Template){
        this.template = template

        params = MutableList(template.list.size) { i ->
            if (template.list[i].type == 'f')
                FloatParam(0f, template.list[i].key)
            else
                StringParam("Пусто",  template.list[i].key)
        }

    }

    fun FindParam(key: String): Param{
        for(p in params)
            if(p.key == key)
                return p;
        return NullParam(key)
    }

    //сравнение для сортировки
    fun Greater(car: Car, key : String) : Boolean{
        val p1 = FindParam(key)
        val p2 = car.FindParam(key)

        if(p1.priority() > p2.priority())
            return true
        else if(p1.priority() < p2.priority())
            return false

        return p1.Greater(p2)
    }

    fun Less(car: Car, key : String) : Boolean{
        val p1 = FindParam(key)
        val p2 = car.FindParam(key)

        if(p1.priority() > p2.priority())
            return false
        else if(p1.priority() < p2.priority())
            return true

        return p1.Less(p2)
    }

    fun Equal(car: Car, key : String) : Boolean{
        val p1 = FindParam(key)
        val p2 = car.FindParam(key)

        return p1.priority() == p2.priority() && p1.Equal(p2)
    }
}
