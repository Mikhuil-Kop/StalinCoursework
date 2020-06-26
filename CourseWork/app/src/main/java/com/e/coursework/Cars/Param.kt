package com.e.coursework.Cars


abstract class Param (var key: String){
    abstract fun priority(): Int
    abstract fun absValue(): Any

    abstract fun Equal(param: Param) : Boolean
    abstract fun Less(param: Param) : Boolean
    abstract fun Greater(param: Param) : Boolean
}

//0
class StringParam(var value: String, key: String) : Param(key){
    override fun priority(): Int {return 0}
    override fun absValue(): Any{return value}

    override fun Equal(param: Param): Boolean {
        var i = 0
        val param = param as StringParam

        if(this.value.length != param.value.length)
            return false

        while (i < this.value.length && i < param.value.length){
            if(this.value[i] != param.value[i])
                return false
            i++
        }
        return true
    }

    override fun Less(param: Param): Boolean {
        var i = 0
        val param = param as StringParam

        while (i < this.value.length && i < param.value.length){
            if(this.value[i] < param.value[i])
                return true
            i++
        }
        return false
    }

    override fun Greater(param: Param): Boolean {
        var i = 0
        val param = param as StringParam

        while (i < this.value.length && i < param.value.length){
            if(this.value[i] > param.value[i])
                return true
            i++
        }
        return false
    }

}

//1
class FloatParam(var value: Float, key: String) : Param(key){
    override fun priority(): Int {return 1}
    override fun absValue(): Any{return value}

    override fun Equal(param: Param): Boolean {
        return this.value == (param as FloatParam).value;
    }

    override fun Less(param: Param): Boolean {
        return this.value < (param as FloatParam).value;
    }

    override fun Greater(param: Param): Boolean {
        return this.value > (param as FloatParam).value;
    }

}

class NullParam(key: String) : Param(key){
    override fun priority(): Int {
        return Int.MIN_VALUE
    }

    override fun absValue(): Any {
        return "NONE"
    }

    override fun Equal(param: Param): Boolean {
        return true
    }

    override fun Less(param: Param): Boolean {
       return false
    }

    override fun Greater(param: Param): Boolean {
        return false
    }

}
