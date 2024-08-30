package com.example.myshoppinglistapp

import androidx.compose.ui.text.toUpperCase

fun main(){
    var addd:(Int,Int)->Int={num1:Int,num2:Int->num1+num2}
    var name:String="Sarvvesh"
    name?.let {
        name=it.toUpperCase()

    }
    print(name)












}
data class human(var  name:String,var age:Int)


fun loggit(a:()->Unit,b:Int){

    println("Helllo world")
    a()
    println(b)
    println("Helloworlf")

}


