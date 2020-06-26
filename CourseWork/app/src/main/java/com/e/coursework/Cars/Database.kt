package com.e.coursework.Cars

import android.content.Context
import android.util.Log
import java.io.File
import java.io.InputStream


class Database {
    var cars = mutableListOf<Car>()
    var templates = mutableListOf<Template>()
    var params = mutableListOf<String>()

    fun Quicksort(items:List<Car>, key: String):List<Car> {
        if (items.count() < 2) {
            return items
        }
        val pivot = items[items.count() / 2]

        val equal = items.filter { it.Equal(pivot, key) }
        val less = items.filter { it.Less(pivot, key) }
        val greater = items.filter { it.Greater(pivot, key) }

        return Quicksort(less, key) + equal + Quicksort(greater, key)
    }

    fun SaveToFile(mcoContext: Context) {
        val file = File(mcoContext.filesDir, "car_base_dir")
        if (!file.exists())
            file.mkdir()

        File(file, "database.txt").printWriter().use { out ->
            out.println(templates.size)
            for(t in templates){
                out.println(t.name)
                out.println(t.list.size)
                for(p in t.list) {
                    out.println(p.key)
                    out.println(p.type)
                }
            }

            out.println(cars.size)
            for(car in cars) {
                var number = -1
                for (t in 0 until templates.size) {
                    if (templates[t] == car.template) {
                        number = t
                        break
                    }
                }
                out.println(number)

                out.println(car.params.size)
                for(p in car.params) {
                    out.println(p.priority())
                    out.println(p.absValue())
                    out.println(p.key)
                }
            }

            out.println(params.size)
            for (p in params)
                out.println(p)

        }
    }

    fun LoadFromFile(mcoContext: Context) {
        cars.clear()
        templates.clear()
        params.clear()

        val file = File(mcoContext.filesDir, "car_base_dir")

        if (!file.exists())
            return
        val inputStream: InputStream = File(file, "database.txt").inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        var index = 0

        fun it() : String{
            index++
            return lineList[index - 1]
        }

        var len = it().toInt()
        for (i in 0 until len) {
            templates.add(Template())
            templates[i].name = it()
            templates[i].list.removeAt(0)

            val len2 = it().toInt()
            for (j in 0 until len2)
                templates[i].list.add(Template.TemplateParam(it(), it()[0]))
        }

        len = it().toInt()
        for (i in 0 until len) {
            cars.add(Car(templates[it().toInt()]))
            val len2 = it().toInt()
            for (j in 0 until len2) {
                val pr = it().toInt()
                if(pr == 0) {
                    cars[i].params.add(StringParam(it(), it()))
                }else{
                    cars[i].params.add(FloatParam(it().toFloat(), it()))
                }
            }
        }

        len = it().toInt()
        for (i in 0 until len)
            params.add(it())
    }

    fun logFile(mcoContext: Context){
        val file = File(mcoContext.filesDir, "car_base_dir")

        if (!file.exists())
            return
        val inputStream: InputStream = File(file, "database.txt").inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
        lineList.forEach{ it -> Log.d("---", it)}

    }



}

val DATABASE = Database()