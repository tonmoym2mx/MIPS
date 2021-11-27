package com.tonmoy.gakk.meow.mips.data


fun main(){
   // val fvd= FinalVersionOfDivision(7,2,4)
   // fvd.calculate()

   // subBinary(3,2,4)
    if(-10<0){
        println("Incorrect value of a and b");
    }else{
        println("Correct value of a and b");
    }


}
class FinalVersionOfDivision(private val dividend:Long,private val divisor:Long,private val bit:Int){

    var bDivisor:String = "${divisor.toBinary(bit)}"
    var bRemainder:String = dividend.toBinary(bit*2)



    fun calculate() {

        printTitle()
        printStep("Initial Value")
        bRemainder = bRemainder.leftShift(1)
        printStep("Shift Remainder left 1")
        printLine(150)
        for(i in 1 .. bit){
            step()
            printLine(150)
        }
        bRemainder = bRemainder.replaceLeftHalf(bRemainder.leftHalf().rightShift(1))
        printStep("Shift left half of Raim right 1")

        println("${dividend/divisor}  ${dividend%divisor}")


    }
    fun step(){

        val result = (bRemainder.leftHalf() sub  bDivisor).binaryFormat(bit)
        bRemainder = bRemainder.replaceLeftHalf(result)
        printStep("Remainder = Remainder - Divisor ")

        if(bRemainder.isOneMSB()){
            bRemainder = bRemainder.replaceLeftHalf(bDivisor add bRemainder.leftHalf()).binaryFormat(bit*2)
            bRemainder = bRemainder.leftShift(1)
            bRemainder = bRemainder.replaceLSB('0')
            printStep("Raim < 0 -> Raim + div,sll R , R0 = 0 ")
        }else{
            bRemainder = bRemainder.leftShift(1)
            bRemainder = bRemainder.replaceLSB('1')
            printStep("Raim >= 0 -> sll R , R0 = 1 ")
        }

    }

    fun printStep(text:String){
        println("${text.textFormat(60)}\t${bDivisor.addBinarySpace(bit)}\t${bRemainder.addBinarySpace(bit)}")
    }
    fun printTitle(){
        println()
        println("Final Version Of Division")
        println("Given That")
        println("Dividend:$dividend,Divisor:$divisor,Bit:$bit")
        println()
        println("${"Step".textFormat(60)}\t${"Divisor".textFormat((bit))}\t${"Remainder".textFormat((bit*2)+1)}")
        printLine(150)
    }
}






























class FirstVersionOfDivision(private val dividend:Long,private val divisor:Long,private val bit:Int){
    var bQuotient:String = "0".binaryFormat(bit)
    var bDivisor:String = "${divisor.toBinary(bit)}${"0".binaryFormat(bit)}"
    var bRemainder:String = dividend.toBinary(bit*2)



    fun calculate() {

        printTitle()
        printStep("Initial Value")

        for(i in 0 .. bit){
            step()
            printLine(150)
        }
        println("${dividend/divisor}  ${dividend%divisor}")


    }
    fun step(){
        bRemainder = bRemainder sub bDivisor
        printStep("Remainder = Remainder - Divisor")

        if(bRemainder.isOneMSB()){
            bRemainder =  (bRemainder add bDivisor).binaryFormat(bit*2)
            bQuotient = bQuotient.leftShift(1)
            bQuotient = bQuotient.replaceLSB('0')
            printStep("Raim < 0 -> Raim + div,sll Q , Q0 = 0 ")
        }else{
            bQuotient = bQuotient.leftShift(1)
            bQuotient = bQuotient.replaceLSB('1')
            printStep("Raim >= 0 -> sll Q , Q0 = 1 ")
        }
        bDivisor = bDivisor.rightShift(1)
        printStep("Shift Div right")
    }

    fun printStep(text:String){
        println("${text.textFormat(60)}\t${bQuotient}\t${bDivisor.addBinarySpace(bit)}\t${bRemainder.addBinarySpace(bit)}")
    }
    fun printTitle(){
        println()
        println("First Version Of Division")
        println("Given That")
        println("Dividend:$dividend,Divisor:$divisor,Bit:$bit")
        println()
        println("${"Step".textFormat(60)}\t${"Quotient".textFormat(bit)}\t${"Divisor".textFormat((bit*2)+1)}\t${"Remainder".textFormat((bit*2)+1)}")
        printLine(150)
    }
}