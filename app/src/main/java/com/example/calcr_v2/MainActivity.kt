package com.example.calcr_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var button_0: Button
    private lateinit var button_1: Button
    private lateinit var button_2: Button
    private lateinit var button_3: Button
    private lateinit var button_4: Button
    private lateinit var button_5: Button
    private lateinit var button_6: Button
    private lateinit var button_7: Button
    private lateinit var button_8: Button
    private lateinit var button_9: Button
    private lateinit var button_plus: Button
    private lateinit var button_minus: Button
    private lateinit var button_times: Button
    private lateinit var button_divide: Button
    private lateinit var button_root: Button
    private lateinit var button_C: Button
    private lateinit var button_equals: Button

    public var current_value = 0.0
    public var load_value = 0.0


    public var first_value = true

    public var add_mode = false
    public var minus_mode = false
    public var times_mode = false
    public var divide_mode = false
    public var root_mode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables

        var result = findViewById<EditText>(R.id.result)
        button_0 = findViewById(R.id.button_0)
        button_1 = findViewById(R.id.button_1)
        button_2 = findViewById(R.id.button_2)
        button_3 = findViewById(R.id.button_3)
        button_4 = findViewById(R.id.button_4)
        button_5 = findViewById(R.id.button_5)
        button_6 = findViewById(R.id.button_6)
        button_7 = findViewById(R.id.button_7)
        button_8 = findViewById(R.id.button_8)
        button_9 = findViewById(R.id.button_9)
        button_plus = findViewById(R.id.button_plus)
        button_minus = findViewById(R.id.button_minus)
        button_times = findViewById(R.id.button_times)
        button_divide = findViewById(R.id.button_divide)
        button_root = findViewById(R.id.button_root)
        button_C = findViewById(R.id.button_C)
        button_equals = findViewById(R.id.button_equals)

        // Helper function for checking if the current number is whole.
        // If yes, displays as an int. Avoids unnecessary decimal points.
        fun currentToInt() {
            if (current_value % 1 == 0.0) result.setText(current_value.toInt().toString())
            else result.setText(current_value.toString())
        }

        currentToInt()

        //ERROR MESSAGE
        fun complain(s: String) {
            load_value = 0.0
            current_value = 0.0
            Toast.makeText(
                this,
                s,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun check() {
            var good = false //Sees if we can proceed normally with the calculation

            //*checks if the loaded value is invalid*
            var typed = result.getText().toString()
            var i = 0
            for (c in typed){
                if (c.equals('.')) i += 1
            }
            //ERROR will happen when
            // if '.' is in the beginning, occurs more than twice,
            // if the load value is empty
            // if there is another active mode

            if (typed.isEmpty()) complain("Please enter your value")
            else if (i > 1 || typed[0].equals('.') || typed[typed.length-1].equals('.') ) complain("Please make a proper decimal")
            else if (add_mode == true || minus_mode == true || times_mode == true || divide_mode == true) complain("Please press 1 operator at a time")
            else {
                good = true
                load_value = typed.toDouble()
            }

            if (good == true) {
                if (first_value == true) {
                    first_value = false
                    current_value = load_value
                } else if (add_mode == true) {
                    current_value += load_value
                } else if (minus_mode == true) {
                    current_value -= load_value
                } else if (times_mode == true) {
                    current_value *= load_value
                } else if (divide_mode == true){
                    if (load_value == 0.0) complain("Please divide by a non-zero number")
                    else current_value /= load_value
                } else if (root_mode == true){
                    if (load_value < 0.0) complain("Please square-root a non-negative number")
                    else current_value = sqrt(current_value)
                }
            }
        }

        fun load(n: Double) {
            load_value *= 10
            load_value += n
            if (load_value % 1 == 0.0)
                result.setText(load_value.toInt().toString())
            else result.setText(load_value.toString())

        }

        button_0.setOnClickListener{
            load(0.0)
        }

        button_1.setOnClickListener{
            load(1.0)
        }

        button_2.setOnClickListener{
            load(2.0)
        }

        button_3.setOnClickListener{
            load(3.0)
        }

        button_4.setOnClickListener{
            load(4.0)
        }

        button_5.setOnClickListener{
            load(5.0)
        }

        button_6.setOnClickListener{
            load(6.0)
        }

        button_7.setOnClickListener{
            load(7.0)
        }

        button_8.setOnClickListener{
            load(8.0)
        }

        button_9.setOnClickListener{
            load(9.0)
        }

        button_plus.setOnClickListener{
            check()
            currentToInt()
            add_mode = true
            load_value = 0.0
        }

        button_minus.setOnClickListener{
            check()
            currentToInt()
            minus_mode = true
            load_value = 0.0
        }

        button_times.setOnClickListener{
            check()
            currentToInt()
            times_mode = true
            load_value = 0.0
        }

        button_divide.setOnClickListener{
            check()
            currentToInt()
            divide_mode = true
            load_value = 0.0
        }

        button_root.setOnClickListener{
            check()
            currentToInt()
            root_mode = true
            load_value = 0.0
        }

        button_equals.setOnClickListener{
            check()

            //reset (fixed by Jonah)
            load_value = current_value
            first_value = true
            add_mode = false
            minus_mode = false
            times_mode = false
            divide_mode = false
            currentToInt()
        }

        button_C.setOnClickListener{

            //reset
            load_value = 0.0
            current_value = 0.0
            first_value = true
            add_mode = false
            minus_mode = false
            times_mode = false
            divide_mode = false
            currentToInt()
        }
    }
}