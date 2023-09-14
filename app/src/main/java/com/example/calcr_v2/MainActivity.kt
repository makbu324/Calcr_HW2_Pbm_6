package com.example.calcr_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
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

    public var current_value = 0
    public var load_value = 0

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

        //
        result.setText(current_value.toString())
        //

        fun check() {
            load_value = Integer.parseInt(result.getText().toString())
            //check if we entered the first value
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
                current_value /= load_value
            } else if (root_mode == true){
                //As of right now the sqrt will only return whole numbers.
                current_value = sqrt(current_value.toDouble()).toInt()
            }
        }

        fun load(n: Int) {
            load_value *= 10
            load_value += n
            result.setText(load_value.toString())
        }

        button_1.setOnClickListener{
            load(1)
        }

        button_2.setOnClickListener{
            load(2)
        }

        button_3.setOnClickListener{
            load(3)
        }

        button_4.setOnClickListener{
            load(4)
        }

        button_5.setOnClickListener{
            load(5)
        }

        button_6.setOnClickListener{
            load(6)
        }

        button_7.setOnClickListener{
            load(7)
        }

        button_8.setOnClickListener{
            load(8)
        }

        button_9.setOnClickListener{
            load(9)
        }

        button_plus.setOnClickListener{
            check()
            result.setText(current_value.toString())
            add_mode = true
            load_value = 0
        }

        button_minus.setOnClickListener{
            check()
            result.setText(current_value.toString())
            minus_mode = true
            load_value = 0
        }

        button_times.setOnClickListener{
            check()
            result.setText(current_value.toString())
            times_mode = true
            load_value = 0
        }

        button_divide.setOnClickListener{
            check()
            result.setText(current_value.toString())
            divide_mode = true
            load_value = 0
        }

        button_root.setOnClickListener{
            check()
            result.setText(current_value.toString())
            root_mode = true
            load_value = 0
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
            result.setText(current_value.toString())
        }

        button_C.setOnClickListener{

            //reset
            load_value = 0
            current_value = 0
            first_value = true
            add_mode = false
            minus_mode = false
            times_mode = false
            divide_mode = false
            result.setText(current_value.toString())
        }
    }
}