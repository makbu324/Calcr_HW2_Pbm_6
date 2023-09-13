package com.example.calcr_v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var button_1: Button
    private lateinit var button_2: Button
    private lateinit var button_3: Button
    private lateinit var button_plus: Button
    private lateinit var button_minus: Button
    private lateinit var button_times: Button
    private lateinit var button_C: Button
    private lateinit var button_equals: Button

    public var current_value = 0
    public var load_value = 0

    public var first_value = true

    public var add_mode = false
    public var minus_mode = false
    public var times_mode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables

        var result = findViewById<EditText>(R.id.result)
        button_1 = findViewById(R.id.button_1)
        button_2 = findViewById(R.id.button_2)
        button_3 = findViewById(R.id.button_3)
        button_plus = findViewById(R.id.button_plus)
        button_minus = findViewById(R.id.button_minus)
        button_times = findViewById(R.id.button_times)
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

        button_equals.setOnClickListener{
            check()

            //reset (fixed by Jonah)
            load_value = current_value
            first_value = true
            add_mode = false
            minus_mode = false
            times_mode = false
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
            result.setText(current_value.toString())
        }
    }
}