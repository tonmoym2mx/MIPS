package com.tonmoy.gakk.meow.mips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.tonmoy.gakk.meow.mips.R
import com.tonmoy.gakk.meow.mips.data.instructionFromString
import com.tonmoy.gakk.meow.mips.databinding.ActivityMainBinding
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.button.setOnClickListener {
            if(binding.inputCode.text.trim().isNotEmpty()){
                try {
                    val inst = binding.inputCode.text.toString().lowercase().trim()
                    val ins =  instructionFromString(inst)



                    val resultText = ins.instructionFormat() +"\n\n" +
                            ins.instruction() + "\n\n"+
                            "Binary:"+ ins.binaryInstruction()
                    binding.result.setText(resultText)
                }catch (e:Exception){
                    Toast.makeText(this, "Compile Error", Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(this, "Please Write Instruction", Toast.LENGTH_SHORT).show();
            }
        }





    }
}