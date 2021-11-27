package com.tonmoy.gakk.meow.mips.data

import java.lang.NullPointerException
import java.lang.NumberFormatException
import java.util.*


infix fun String.add(binary:String): String {
    return GFG.addBinary(this,binary)
}
infix fun String.sub(binary: String): String {
    val bin2 = binary.twosComplement(this.length)
    return (this add bin2).binaryFormat(this.length)
}



fun String.twosComplement(len: Int): String = GFG.printTwosComplement(this).binaryFormat(len)
fun String.binaryFormat(len:Int): String {
     val str = String.format("%" + len + "s", this)
         .replace(" ".toRegex(), "0")

    return str.substring(str.length-len)

}
fun String.textFormat(len:Int): String {
    val str = String.format("%" + len + "s", this)
    return str.substring(0,len)

}
fun line(len:Int): String {
    val str = String.format("%" + len + "s", "-")
        .replace(" ".toRegex(), "-")

    return str.substring(str.length-len)

}
fun printLine(len: Int) = println("${line(len)}")
fun String.leftShift(bit:Int): String {
    return this.binaryToLong().shl(bit).toBinary(this.length).binaryFormat(this.length)
}
fun String.rightShift(bit:Int): String {
    return this.binaryToLong().shr(bit).toBinary(this.length).binaryFormat(this.length)
}
fun String.leftHalf(): String {
   val bit =  (this.length/2)
  return this.addBinarySpace(bit).split(" ").first()
}
fun String.replaceLeftHalf(binary: String): String {
    return "${binary}${this.rightHalf()}"
}
fun String.replaceRightHalf(binary: String): String {
    return "${this.leftHalf()}${binary}"
}
fun String.rightHalf(): String {
    val bit =  (this.length/2)
    return this.addBinarySpace(bit).split(" ").last()
}
fun String.rightHalf(size:Int): String {
    return this.addBinarySpace(size).split(" ").last()
}fun String.leftHalf(size:Int): String {
    return this.addBinarySpace(size).split(" ").first()
}
fun String.isOneLSB(): Boolean = this.last() == '1'
fun String.isOneMSB(): Boolean = this.first() == '1'
fun String.replaceMSB(char: Char):String{
    val list = this.toMutableList().apply {
        this[0] = char
    }
   return String(list.toCharArray())
}
fun String.replaceLSB(char: Char): String {
    val list =  this.toMutableList().apply {
        this[this@replaceLSB.lastIndex] = char
    }
    return String(list.toCharArray())
}
/*fun String.addBinarySpace(bit:Int): String {
    val list = this.toCharArray().toMutableList()
    val newList:MutableList<Char > = ArrayList()
    list.forEachIndexed { index, c ->
        newList.add(c)
        if(index%2==0){
            newList.add(' ')
        }
    }


    return  String(newList.toCharArray())
}*/
fun String.removeSpace(): String = this.replace(" ","")

fun String.addBinarySpace(step:Int): String {

    var newString:String = ""
    for(index in this.length-1 downTo 0){
        val i = index+1
        if(i%step==0 && i !=this.length){
            newString +=" "
        }
        newString +=this[index]
    }
   return newString.reversed()
}
fun String?.fx() = this ?: ""

inline fun<T> exH(func:()->T):T?{
    return try {
        func.invoke()
    }catch (e:java.lang.Exception){

        null
    }
}


















