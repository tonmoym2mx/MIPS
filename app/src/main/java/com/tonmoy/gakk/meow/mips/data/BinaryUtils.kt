package com.tonmoy.gakk.meow.mips.data

import android.text.TextUtils


infix fun String.add(binary:String): String {
    return GFG.addBinary(this,binary)
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
fun String.isFirstBitOne(): Boolean = this.last() == '1'
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

















