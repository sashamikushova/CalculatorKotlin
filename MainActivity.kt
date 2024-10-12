package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var input = ""
    private var operator = ""
    private var firstValue = 0.0
    private var secondValue = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.display)

        val btn0: Button = findViewById(R.id.btn_0)
        val btn1: Button = findViewById(R.id.btn_1)
        val btn2: Button = findViewById(R.id.btn_2)
        val btn3: Button = findViewById(R.id.btn_3)
        val btn4: Button = findViewById(R.id.btn_4)
        val btn5: Button = findViewById(R.id.btn_5)
        val btn6: Button = findViewById(R.id.btn_6)
        val btn7: Button = findViewById(R.id.btn_7)
        val btn8: Button = findViewById(R.id.btn_8)
        val btn9: Button = findViewById(R.id.btn_9)
        val btnAdd: Button = findViewById(R.id.btn_add)
        val btnSubtract: Button = findViewById(R.id.btn_subtract)
        val btnMultiply: Button = findViewById(R.id.btn_multiply)
        val btnDivide: Button = findViewById(R.id.btn_divide)
        val btnClear: Button = findViewById(R.id.btn_clear)
        val btnEquals: Button = findViewById(R.id.btn_equals)
        val btnPlusMinus: Button = findViewById(R.id.btn_plus_minus)
        val btnPercent: Button = findViewById(R.id.btn_percent)
        val btnComma: Button = findViewById(R.id.btn_comma)
        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (button in numberButtons) {
            button.setOnClickListener {
                val value = (it as Button).text.toString()
                input += value
                display.text = input
            }
        }

        btnAdd.setOnClickListener { handleOperator("+") }
        btnSubtract.setOnClickListener { handleOperator("-") }
        btnMultiply.setOnClickListener { handleOperator("*") }
        btnDivide.setOnClickListener { handleOperator("/") }

        btnClear.setOnClickListener {
            input = ""
            firstValue = 0.0
            secondValue = 0.0
            operator = ""
            display.text = "0"
        }

        btnEquals.setOnClickListener {
            if (operator.isNotEmpty() && input.isNotEmpty()) {
                secondValue = input.toDouble()
                val result = calculate(firstValue, secondValue, operator)
                display.text = result.toString()
                input = result.toString()
                operator = ""
            }
        }

        btnPlusMinus.setOnClickListener {
            if (input.isNotEmpty()) {
                if (input.startsWith("-")) {
                    input = input.removePrefix("-")
                } else {
                    input = "-$input"
                }
                display.text = input
            }
        }

        btnPercent.setOnClickListener {
            if (input.isNotEmpty()) {
                val value = input.toDouble() / 100
                input = value.toString()
                display.text = input
            }
        }

        btnComma.setOnClickListener {
            if (!input.contains(".")) {
                input += "."
                display.text = input
            }
        }
    }

    private fun handleOperator(op: String) {
        if (input.isNotEmpty()) {
            firstValue = input.toDouble()
            input = ""
            operator = op
        }
    }

    private fun calculate(firstValue: Double, secondValue: Double, operator: String): Any {
        val result = when (operator) {
            "+" -> firstValue + secondValue
            "-" -> firstValue - secondValue
            "*" -> firstValue * secondValue
            "/" -> if (secondValue != 0.0) firstValue / secondValue else 0.0
            else -> 0.0
        }
        return if (result % 1 == 0.0) result.toInt() else result
    }
}
