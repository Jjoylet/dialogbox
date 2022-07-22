package com.example.dialogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonBasic : Button = findViewById(R.id.buttonBasic)
        val buttonCustom : Button = findViewById(R.id.buttonCustom)

        buttonBasic.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure you want to delete?")
            builder.setMessage("Once you deleted  photo , you wont be able to recover")
            builder.setPositiveButton("Yes"){_, _ ->
                Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel"){_, _->
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
            }
           // builder.create().show()
            //Make sure it is not cancellable
            val alert : AlertDialog = builder.create()
            alert.setCancelable(false)
            alert.show()

        }

        buttonCustom.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val layout = layoutInflater.inflate(R.layout.custom_dialog,null)
            builder.setTitle("Interest Calculator")
            val edtPrincipal: EditText = layout.findViewById(R.id.edtPrincipal)
            val edtPeriod: EditText = layout.findViewById(R.id.edtPeriod)
            val edtRate: EditText = layout.findViewById(R.id.edtRate)

            builder.setView(layout)

            builder.setPositiveButton("Calculate"){_, _->
                val principal = edtPrincipal.text.toString().trim().toDoubleOrNull()
                val period = edtPeriod.text.toString().trim().toDoubleOrNull()
                val rate =edtRate.text.toString().trim().toDoubleOrNull()

                if (principal != null && period!=null && rate !=null){
                    calculateInterest(principal,rate,period)
                }else{
                    Toast.makeText(this,"Invalid values",Toast.LENGTH_SHORT).show()
                }


               // if (principal.isNotEmpty() && period.isNotEmpty() && rate.isNotEmpty()){
                   // calculateInterest(principal.toDouble(), rate.toDouble(),period.toDouble(),2)


            }
            builder.show()
        }
    }

    fun calculateInterest(principal: Double, rate:Double ,period: Double, numberTimes: Int = 1){
        val base = numberTimes * 100
        var amount = principal * (1 + rate / 100).pow(period * numberTimes)
        Toast.makeText(this,"KES $amount",Toast.LENGTH_LONG).show()
        Log.d("INTEREST","$amount")


    }
}