package com.example.calcr_v2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var button_decimal: Button
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
    public var number_typed = true
    public var decimal_mode = false
    public var recent_decimal = false
    public var equals_mode = false

    @SuppressLint("SetTextI18n")
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
        button_decimal = findViewById(R.id.button_decimal)
        button_C = findViewById(R.id.button_C)
        button_equals = findViewById(R.id.button_equals)

        // Helper function for checking if the current number is whole.
        // If yes, displays as an int. Avoids unnecessary decimal points.
        fun currentToInt() {
            if (current_value % 1 == 0.0) result.setText(current_value.toInt().toString())
            else result.setText(current_value.toString())
        }

        fun reset() {
            load_value = 0.0
            current_value = 0.0
            first_value = true
            add_mode = false
            minus_mode = false
            times_mode = false
            divide_mode = false
            decimal_mode = false
            recent_decimal = false
            equals_mode = false
            currentToInt()
        }

        currentToInt()

        //ERROR MESSAGE
        fun complain(s: String): Boolean {
            load_value = 0.0
            current_value = 0.0
            Toast.makeText(
                this,
                s,
                Toast.LENGTH_SHORT
            ).show()
            reset()
            return false
        }

        fun check(): Boolean {
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

            //if the load_value is different from the edittext value then something must have been changed -> number_typed = true
            if (typed.toDouble() != load_value) number_typed = true

            if (typed.isEmpty()) complain("Please enter your value")
            else if (i > 1 || typed[0].equals('.') || typed[typed.length-1].equals('.') ) complain("Please make a proper decimal")
            else if (number_typed == false) complain("Please don't use two operators at the same time")
            else {
                good = true
                load_value = typed.toDouble()
                decimal_mode = false
                recent_decimal = false
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
                if (current_value > 99999999) {
                    complain("The number is too high!")
                    return false
                }
                else if (current_value < -9999999){
                    complain("The number is too low!")
                    return false

                }
                else {
                    number_typed = false
                    return true
                }
            } else return false
        }

        //test

        fun load(n: Double) {
            if (!equals_mode) {
                if (result.getText().toString().length < 10000000 && load_value < 99999999) {
                    if (decimal_mode) {
                        if (recent_decimal) {
                            load_value = (result.text.toString() + n.toInt().toString()).toDouble()
                        } else {
                            load_value = (load_value.toString() + n.toInt().toString()).toDouble()
                        }
                        result.setText(load_value.toString())
                        number_typed = true
                    } else {
                        load_value *= 10
                        load_value += n
                        if (load_value % 1 == 0.0)
                            result.setText(load_value.toInt().toString())
                        else result.setText(load_value.toString())
                        number_typed = true
                    }
                }
            }
            else {
                load_value = 0.0
                load_value += n
                result.setText(load_value.toInt().toString())
                number_typed = true
                equals_mode = false
            }

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
            if (check()) {
                currentToInt()
                add_mode = true
                load_value = 0.0
            }
        }

        button_minus.setOnClickListener{
            if (check()) {
                currentToInt()
                minus_mode = true
                load_value = 0.0
            }
        }

        button_times.setOnClickListener{
            if (check()) {
                currentToInt()
                times_mode = true
                load_value = 0.0
            }
        }

        button_divide.setOnClickListener{
            if (check()) {
                currentToInt()
                divide_mode = true
                load_value = 0.0
            }
        }

        button_root.setOnClickListener{
            if (check()) {
                currentToInt()
                root_mode = true
                load_value = 0.0
            }
        }

        button_decimal.setOnClickListener {
            if (load_value % 1 == 0.0) {
                result.setText(load_value.toInt().toString() + ".")
                decimal_mode = true
                recent_decimal = true
            }
            if (equals_mode) {
                result.setText("0.")
                load_value = 0.0
                decimal_mode = true
                recent_decimal = true
                equals_mode = false
            }

        }


        button_equals.setOnClickListener{
            if (check()) {
                //reset (fixed by Jonah)
                load_value = current_value
                first_value = true
                add_mode = false
                minus_mode = false
                times_mode = false
                divide_mode = false
                number_typed = true
                decimal_mode = false
                recent_decimal = false
                equals_mode = true
                currentToInt()
            }
        }

        button_C.setOnClickListener{

            //reset
            reset()
        }
    }
}