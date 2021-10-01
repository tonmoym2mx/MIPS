package com.tonmoy.gakk.meow.mips.data


fun main(){


    val fvm = FirstVersionOfMultiplication(2,5,4)
    val rvm = RefinedVersionOfMultiplication(2,5,4)
    fvm.calculate()
    rvm.calculate()




}



class RefinedVersionOfMultiplication(private val multiplier:Long, private val multiplicand:Long, private val bit:Int){
    private var bMultiplier = multiplier.toBinary(bit)
    private var bMultiplicand = multiplicand.toBinary(bit)

    private var bProduct1 = bMultiplier.binaryFormat(bit)
    private var bProduct2 = "0".binaryFormat(bit)

    private val sumProduct
        get() = "$bProduct2$bProduct1"

    private var bProduct:String = sumProduct


    fun calculate(){
        printTitle()
        printStep("Initial Value")
        printLine(100)

        for(i in 1 .. bit){
            step()
           printLine(100)
        }

    }
    fun step(){
        if(bProduct.isFirstBitOne()){

            bProduct2 = (bProduct2 add bMultiplicand).binaryFormat(bit)
            bProduct = sumProduct
            printStep("1 -> Product = Product + Multiplicand")
        }else{
            printStep("0 -> No operation")
        }


        bProduct = bProduct.rightShift(1)
        printStep("Shift right Product")
        bProduct1 = bProduct.addBinarySpace(bit).split(" ")[1]
        bProduct2 = bProduct.addBinarySpace(bit).split(" ")[0]


    }





    fun printStep(text:String){
        println("${text.textFormat(50)}\t${bMultiplicand}\t${bProduct.addBinarySpace(bit)}")
    }
    fun printTitle(){
        println()
        println("Refined Version Of Multiplication")
        println("Given That")
        println("Multiplier:$multiplier,Multiplicand:$multiplicand,Bit:$bit")
        println()
        println("${"Step".textFormat(50)}\t${"Mcand".textFormat((bit)+1)}\t${" Product".textFormat((bit*2)+1)}")
        printLine(100)
    }
}



class FirstVersionOfMultiplication(private val multiplier:Long, private val multiplicand:Long, private val bit:Int){
    private var bMultiplier = multiplier.toBinary(bit)
    private var bMultiplicand = multiplicand.toBinary(bit*2)
    private var bProduct:String  = "0".binaryFormat(bit*2)

    fun calculate(){
        printTitle()
        printStep("Initial Value")
        printLine(100)

        for(i in 1 .. bit){
            step()
            printLine(100)
        }

    }
    fun step(){
        if(bMultiplier.isFirstBitOne()){
            bProduct = bProduct add bMultiplicand
            printStep("1 -> Product = Product + Multiplicand")
        }else{
            printStep("0 -> No operation")
        }
        bMultiplicand = bMultiplicand.leftShift(1)
        printStep("Shift left Multiplicand")
        bMultiplier = bMultiplier.rightShift(1)
        printStep("Shift right Multiplier")
    }





    fun printStep(text:String){
        println("${text.textFormat(50)}\t$bMultiplier\t${bMultiplicand}\t${bProduct}")
    }
    fun printTitle(){
        println()
        println("First Version Of Multiplication")
        println("Given That")
        println("Multiplier:$multiplier,Multiplicand:$multiplicand,Bit:$bit")
        println()
        println("${"Step".textFormat(50)}\t${"Multiplier".textFormat(bit)}\t${"Multiplicand".textFormat((bit*2)+1)}\t${"Product".textFormat((bit*2)+1)}")
        printLine(100)
    }
}





fun subBinary(num1:Long,num2:Long,length:Int){
    println("   Sub ($num1 - $num2)")
    val bin1 = num1.toBinary(length)
    println("   $bin1")
    val bin2 = num2.toBinary(length).twosComplement(length)
    println("+  $bin2")
    val result = bin1 add bin2
    println("   ${line(length)}")
    println("   ${result?.binaryFormat(length)} (${result?.binaryFormat(length)?.binaryToLong()})")
}

fun addBinary(num1:Long,num2:Long,length:Int){
    println("   Add ($num1 + $num2)")
    val bin1 = num1.toBinary(length)
    println("   $bin1")
    val bin2 = num2.toBinary(length)
    println("+  $bin2")
    val result = bin1 add bin2
    println("   ${line(length)}")
    println("   ${result?.binaryFormat(length)} (${result?.binaryFormat(length)?.binaryToLong()})")
}