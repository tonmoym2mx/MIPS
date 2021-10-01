package com.tonmoy.gakk.meow.mips.data

import java.lang.Exception
import java.lang.NullPointerException
import java.lang.RuntimeException

fun main(){
//for(i=0; i<a; i++)
//a += b;

    val i = 11
    if(i<=10){
        println("true")
    }else{
        println("false")
    }


   /* println("sw t1,2(t0)".contains("sw"))
    instructionFromString("lw t1,2(t0)").also {
        println(it.instruction())
        println(it.binaryInstruction().replace(" ",""))
    }
    Addu.fromString("addu t0,t1,t2").also {
        println(it.instruction())
        println(it.binaryInstruction().replace(" ",""))
    }
    And.fromString("and t0,t1,t2").also {
        println(it.instruction())
        println(it.binaryInstruction().replace(" ",""))
    }
    Or.fromString("or t0,t1,t2").also {
        println(it.instruction())
        println(it.binaryInstruction().replace(" ",""))
    }
    Nor.fromString("nor t0,t1,t2").also {
        println(it.instruction())
        println(it.binaryInstruction().replace(" ",""))
    }
    Xor.fromString("xor t0,t1,t2").also {
        println(it.instruction())
        println(it.binaryInstruction().replace(" ",""))
    }*/

}
fun instructionFromString(ins:String): Instruction {

    return when {
        ins.contains("lw") -> Lw.fromString(ins)
        ins.contains("sw") ->  Sw.fromString(ins)
        ins.contains("addi") ->  Addi.fromString(ins)
        ins.contains("addu") ->  Addu.fromString(ins)
        ins.contains("add") ->  Add.fromString(ins)
        ins.contains("and") ->  And.fromString(ins)
        ins.contains("xor") ->  Xor.fromString(ins)
        ins.contains("or") ->  Or.fromString(ins)
        ins.contains("nor") ->  Nor.fromString(ins)
        else -> {
            throw RuntimeException("Compile Error ")
        }
    }

}




class Lw(rs: Register, rt: Register, immediate: Int) : TypeI(35,rs, rt, immediate){
    companion object{
        fun fromString(codeStr: String): Lw {


            val map  = codeStr
                .replace("(",",").replace(")","")
                .replace("lw","").trim().split(",").map { s -> s }
            return Lw(
                Register.getFromString(map[0])!!,
                Register.getFromString(map[2])!!,
                map[1].trim().toInt(),
            )
        }
    }
}
class Sw(rs: Register, rt: Register, immediate: Int) : TypeI(43,rs, rt, immediate){
    companion object{
        fun fromString(codeStr: String):Sw{

            val map  = codeStr
                .replace("(",",").replace(")","")
                .replace("sw","").trim().split(",").map { s -> s }
           return Sw(
                Register.getFromString(map[0])!!,
                Register.getFromString(map[2])!!,
                map[1].trim().toInt(),
            )
        }
    }
}
class Addi(rs: Register, rt: Register, immediate: Int): TypeI(8,rs, rt, immediate){
    companion object{
        fun fromString(codeStr:String):Addi{
            val map  = codeStr.replace("addi","").trim().split(",").map { s -> s }
            return Addi(
                Register.getFromString(map[0])!!,
                Register.getFromString(map[1])!!,
                map[2].trim().toInt()
            )
        }
    }
}


class Add(rs: Register, rt: Register,rd: Register): TypeR(rs, rt, rd, 0,32){
    companion object{
       fun fromString(codeStr:String): Add {
           val map  = codeStr.replace("add","").trim().split(",").map { s ->
               Register.getFromString(s)
           }
         return  Add(map[0]!!,map[1]!!,map[2]!!)
        }
    }
}
class Addu(rs: Register, rt: Register,rd: Register): TypeR(rs, rt, rd, 0,33){
    companion object{
        fun fromString(codeStr:String): Addu {
            val map  = codeStr.replace("addu","").trim().split(",").map { s ->
                Register.getFromString(s)
            }
            return  Addu(map[0]!!,map[1]!!,map[2]!!)
        }
    }
}
class And(rs: Register, rt: Register,rd: Register): TypeR(rs, rt, rd, 0,36){
    companion object{
        fun fromString(codeStr:String): And {
            val map  = codeStr.replace("and","").trim().split(",").map { s ->
                Register.getFromString(s)
            }
            return  And(map[0]!!,map[1]!!,map[2]!!)
        }
    }
}
class Xor(rs: Register, rt: Register,rd: Register): TypeR(rs, rt, rd, 0,38){
    companion object{
        fun fromString(codeStr:String): Xor {
            val map  = codeStr.replace("xor","").trim().split(",").map { s ->
                Register.getFromString(s)
            }
            return  Xor(map[0]!!,map[1]!!,map[2]!!)
        }
    }
}
class Or(rs: Register, rt: Register,rd: Register): TypeR(rs, rt, rd, 0,37){
    companion object{
        fun fromString(codeStr:String): Or {
            val map  = codeStr.replace("or","").trim().split(",").map { s ->
                Register.getFromString(s)
            }
            return  Or(map[0]!!,map[1]!!,map[2]!!)
        }
    }
}
class Nor(rs: Register, rt: Register,rd: Register): TypeR(rs, rt, rd, 0,39){
    companion object{
        fun fromString(codeStr:String): Nor {
            val map  = codeStr.replace("nor","").trim().split(",").map { s ->
                Register.getFromString(s)
            }
            return  Nor(map[0]!!,map[1]!!,map[2]!!)
        }
    }
}




interface Instruction{
    val opcode:Int
    val opcodeBits: Int
        get() = 6
    val rsBits: Int
        get() = 5
    val rtBits: Int
        get() = 5
    fun instructionFormat():String
    fun instruction():String
    fun binaryInstruction():String

}


open class TypeR(

    var rs:Register,
    var rt:Register,
    var rd:Register,
    var shamt:Int,
    var functionCode:Int
): Instruction {
    override val opcode:Int = 0
    val  rdBits:Int = 5
    val  functionCodeBits:Int = 6
    val  shamtBits:Int = 5


    /*add t0 t1 t2
    rs, rt, rd,
    Binary: 00000001001010100100000000100000
    Hex: 0x012A4020
    31	26	25	21	20	16	15	11	10	6	5	0
    SPECIAL	t1	t2	t0	0	ADD
    000000	01001	01010	01000	00000	100000
    */

    override fun binaryInstruction(): String {
       return "${opcode.toBinary(opcodeBits)} ${rt.toBinary()} ${rd.toBinary()} ${rs.toBinary()} ${shamt.toBinary(shamtBits)} ${functionCode.toBinary(functionCodeBits)}"

    }
    override fun instruction(): String {
        return "Opcode:$opcode\nrt:${rt.number}\nrd:${rd.number}\nrs:${rs.number}\nshamt:$shamt\nfunc:$functionCode"

    }

    override fun instructionFormat():String {
        return  "R Type [opcode:$opcodeBits bits, rs:$rsBits bits, rt:$rtBits Bits, rd:$rdBits Bits, shamt:$shamtBits bits, func:$functionCodeBits bits]"
    }


}
 open class TypeI(
     override val opcode: Int,
     var rs:Register,
     var rt:Register,
     var immediate:Int,
 ): Instruction {
    //override var opcode:Int = 8
    val immediateBits:Int = 16
    override fun instructionFormat():String {
        return  "I Type [opcode:$opcodeBits bits, rs:$rsBits bits, rt:$rtBits, immediate:$immediateBits bits]"
    }

    override fun instruction(): String {
        return  "Opcode:$opcode\nrt:${rt.number}\nrs:${rs.number}\nimmedite:0x${immediate.toString(16).uppercase()}\n"
    }

    override fun binaryInstruction(): String {
        return  "${opcode.toBinary(opcodeBits)} ${rt.toBinary()} ${rs.toBinary()} ${immediate.toBinary(immediateBits)}"
    }


}

class TypeJ(
    var address:Int
): Instruction {
    override var opcode:Int = 2
    val addressBits:Int = 26
    override fun instructionFormat():String {
        return  "J Type [opcode:$opcodeBits bits ,address:${addressBits} bits ]"
    }

    override fun instruction(): String {
        TODO("Not yet implemented")
    }

    override fun binaryInstruction(): String {
        TODO("Not yet implemented")
    }
}

enum class Register(val number:Int?=null,var value:Int?=null){
    ZERO(0),
    AT(1),
    V0(2),
    V1(3),
    A0(4),
    A1(5),
    A2(6),
    A3(7),
    T0(8),
    T1(9),
    T2(10),
    T3(11),
    T4(12),
    T5(13),
    T6(14),
    T7(15),
    S0(16),
    S1(17),
    S2(18),
    S3(19),
    S4(20),
    S5(21),
    S6(22),
    S7(23),
    T8(24),
    T9(25),
    K0(26),
    K1(27),
    GP(28),
    SP(29),
    FP(30),
    RA(31),
    PC(),
    HI(),
    LO();
    fun toBinary() = number?.toBinary(5)

    companion object{
        fun getFromString(s:String): Register? {
            return try {
                Register.values().first() { register ->
                    register.name.lowercase().contains(s.lowercase().trim())
                }
            }catch (e:Exception){
                null
            }

        }
    }

}
fun Int.toBinary(len: Int): String {
    return String.format(
        "%" + len + "s",
        this.toString(2)
    ).replace(" ".toRegex(), "0")
}
/*fun Long.toBinary(len: Int): String {
    return String.format(
        "%" + len + "s",
        this.toString(2)
    ).replace(" ".toRegex(), "0")
}*/

fun Long.toBinary(len: Int): String {
    return  this.toString(2).binaryFormat(len)
}

fun String.binaryToLong():Long{
   return java.lang.Long.parseLong(this,2)
}