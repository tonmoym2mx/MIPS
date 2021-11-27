package com.tonmoy.gakk.meow.mips.data

import kotlin.math.log2

fun main(){
    //directMappedCaseBookExample()
  // directMappedCaseAssignmentExample()

    //fullyAssociativeCaseBookExample()

   // fullyAssociativeCaseAssignmentExample();

    twoWaySetAssociativeCaseAssignmentExample()
}
fun directMappedCaseBookExample(){
    val dmc = DirectMappedCase(caseBlockSize = 4, mainMemoryAddressBitSize = 4 )
    dmc.accessAndPrint(0)
    dmc.accessAndPrint(8)
    dmc.accessAndPrint(0)
    dmc.accessAndPrint(6)
    dmc.accessAndPrint(8)

}
fun directMappedCaseAssignmentExample(){

    val dmc = DirectMappedCase(caseBlockSize = 8, mainMemoryAddressBitSize = 4 )
    dmc.accessAndPrint(2)
    dmc.accessAndPrint(7)
    dmc.accessAndPrint(2)
    dmc.accessAndPrint(6)
    dmc.accessAndPrint(7)
    dmc.accessAndPrint(15)
}

fun fullyAssociativeCaseBookExample(){
   val fac =  FullyAssociativeCase(8,4)
    fac.accessAndPrint(2)
    fac.accessAndPrint(7)
    fac.accessAndPrint(2)
    fac.accessAndPrint(6)
    fac.accessAndPrint(7)
    fac.accessAndPrint(15)
}
fun fullyAssociativeCaseAssignmentExample(){
    val fac =  FullyAssociativeCase(4,4)
    fac.accessAndPrint(0)
    fac.accessAndPrint(8)
    fac.accessAndPrint(0)
    fac.accessAndPrint(6)
    fac.accessAndPrint(8)
}
fun twoWaySetAssociativeCaseAssignmentExample(){
    val twsa =  TwoWaySetAssociativeCase(
        setSize = 2,
        caseBlockSize = 4,
        mainMemoryAddressBitSize = 4
    )
    twsa.accessAndPrint(0)
    twsa.accessAndPrint(8)
    twsa.accessAndPrint(0)
    twsa.accessAndPrint(6)
    twsa.accessAndPrint(8)
}

class TwoWaySetAssociativeCase(private val setSize:Int,private val caseBlockSize:Int, private val mainMemoryAddressBitSize:Int){
    private val sets: Array<Set> = Array(setSize){ Set(caseBlockSize/setSize) }
    private val tagBitSize = (mainMemoryAddressBitSize - log2(setSize.toFloat()) ).toInt()
    fun access(address: Long): AccessData? {

        val index: Int = (address % setSize).toInt()
        val tag = address.toBinary(mainMemoryAddressBitSize).substring(0,tagBitSize)
        val setsData = sets[index]

       return setsData.access(address,tag)?.apply { setIndex = index }
    }
    fun accessAndPrint(address: Long){
        val data = access(address)
        println("Address: ${address.toBinary(mainMemoryAddressBitSize)} ($address)  SET NO ${data?.setIndex} ${if(data?.isHit == true)"Hit" else "miss"} ${sets[data?.setIndex!!].memory.map { it.toString() }.toString()}")
    }



}
class FullyAssociativeCase(private val caseBlockSize:Int, private val mainMemoryAddressBitSize:Int){

    private val set = Set(caseBlockSize)

    fun accessAndPrint(address: Long){
        val data = set.access(address, address.toBinary(mainMemoryAddressBitSize))
        println("Address: ${address.toBinary(mainMemoryAddressBitSize)} ($address)   ${if(data?.isHit == true)"Hit" else "miss"} ${toString()}")
    }

    override fun toString(): String {
        return set.memory.map { it.toString() }.toString()
    }
}
class Set(private val caseBlockSize:Int){

    val memory: Array<MemoryBlock> = Array(caseBlockSize){ MemoryBlock() }


    fun access(address:Long,tag:String): AccessData? {

        var accessData:AccessData ? = null
        val data = exH { memory.first { it.tag == tag } }
        if(data !=null){
            data.accessTime = System.currentTimeMillis()
          //  println("${data.accessTime}")

            accessData = AccessData(isHit = true,data.data)
        }else{

            val temp = memory.minByOrNull { it.accessTime?:0 }
            temp?.tag  = tag
            temp?.data = "memory[$address]"
            temp?.accessTime = System.currentTimeMillis()

            accessData = AccessData(isHit = false, data =  null)

        }

        return  accessData

    }
}
class DirectMappedCase(private val caseBlockSize:Int, private val mainMemoryAddressBitSize:Int){
    private val memory: Array<MemoryBlock> = Array(caseBlockSize){ MemoryBlock() }

    private val tagBitSize = (mainMemoryAddressBitSize - log2(caseBlockSize.toFloat()) ).toInt()


    fun access(address:Long): AccessData {
        val index: Int = (address % caseBlockSize).toInt()
        val tag = address.toBinary(mainMemoryAddressBitSize).substring(0,tagBitSize)
        val caseData = memory[index]
        return if(caseData.tag == tag){
            AccessData(isHit = true,memory[index].data)
        }else{
            caseData.tag = tag
            caseData.data = dataFromMainMemory(address)
            AccessData(isHit = false,null)
        }

    }
    fun accessAndPrint(address: Long){
        val data = access(address)
        println("Address: ${address.toBinary(mainMemoryAddressBitSize)} ($address)   ${if(data.isHit)"Hit" else "miss"} ${toString()}")
    }
    private fun dataFromMainMemory(address: Long): String {
        //TODO this is the demo function so this function return the same address
        return "memory[$address]"
    }

    override fun toString(): String {
        return memory.map { it.toString() }.toString()
    }
}

data class AccessData(val isHit:Boolean = false, val data:String?= null,var setIndex:Int?=null)
data class MemoryBlock(var tag:String?=null,var data:String?=null ,var accessTime:Long?=0){
    override fun toString(): String {
        return "(${tag.fx()},${data.fx()})"
    }
}
