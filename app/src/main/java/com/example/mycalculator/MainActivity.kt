package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        var accumulator: Double = +0.0
        var current: Double = +0.0
        var currentOperation = "="

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textViewCalculator = findViewById<TextView>(R.id.textCalculator)

        Log.d("buttonDigit0", "R.id.buttonDigit0: ${R.id.buttonDigit0 is Int}");

        val digitIdsList: ArrayList<Int> = ArrayList()
        digitIdsList.add(R.id.buttonDigit0)
        digitIdsList.add(R.id.buttonDigit1)
        digitIdsList.add(R.id.buttonDigit2)
        digitIdsList.add(R.id.buttonDigit3)
        digitIdsList.add(R.id.buttonDigit4)
        digitIdsList.add(R.id.buttonDigit5)
        digitIdsList.add(R.id.buttonDigit6)
        digitIdsList.add(R.id.buttonDigit7)
        digitIdsList.add(R.id.buttonDigit8)
        digitIdsList.add(R.id.buttonDigit9)

        for (id in digitIdsList) {
            val button = findViewById<Button>(id)
            button.setOnClickListener(View.OnClickListener {
                Log.d("buttonDigitListender", "R.id.buttonDigit${id}")

                if (current == 0.0) {
                    textViewCalculator.text = button.text
                    current = (button.text as String).toDouble()
                } else {
                    textViewCalculator.text = "${textViewCalculator.text}${button.text}"
                    current = (textViewCalculator.text as String).toDouble()
                }
            })
        }


        fun handleEquals() {
            when (currentOperation) {
                "+" -> {
                    accumulator += current
                }
                "-" -> {
                    accumulator -= current
                }
                "*" -> {
                    accumulator *= current
                }
                "/" -> {
                    if (accumulator == 0.0 || current == 0.0) {
                        if (accumulator > 0 || current > 0) {
                            textViewCalculator.text = "Infinity"
                        } else {
                            textViewCalculator.text = "-Infinity"
                        }
                        currentOperation = "="
                        return
                    } else if (accumulator == 0.0 && current == 0.0) {
                        textViewCalculator.text = "Undefined"
                        return
                    }
                    accumulator /= current
                }
            }
            current = accumulator
            textViewCalculator.text = "${accumulator}"
            currentOperation = "="
        }

        fun calculate(operator: String) {
            if (currentOperation == operator) {
                return
            }
            if (operator != "=") {
                accumulator = current // TODO only if operator is not =
            }
            textViewCalculator.text = "${textViewCalculator.text}${operator}"
            when (operator) {
                "+" -> {
                    currentOperation = "+"
                    current = 0.0
                }
                "-" -> {
                    currentOperation = "-"
                    current = 0.0
                }
                "*" -> {
                    currentOperation = "*"
                    current = 0.0
                }
                "/" -> {
                    currentOperation = "/"
                    current = 0.0
                }
                "CLR" -> {
                    current = 0.0
                    accumulator = 0.0
                    textViewCalculator.text = "0"
                    currentOperation = "="
                }
                "=" -> {
                    Log.d("shouldEqual", "shouldEqual: calculate(${operator})")
                    handleEquals()
                }
            }
        }

        val operationsIdsList: ArrayList<Int> = ArrayList()
        operationsIdsList.add(R.id.buttonOperationEql)
        operationsIdsList.add(R.id.buttonOperationAdd)
        operationsIdsList.add(R.id.buttonOperationSub)
        operationsIdsList.add(R.id.buttonOperationMul)
        operationsIdsList.add(R.id.buttonOperationClr)
        operationsIdsList.add(R.id.buttonOperationDiv)

        for (id in operationsIdsList) {
            val button = findViewById<Button>(id)
            button.setOnClickListener(View.OnClickListener {
                Log.d("listenderOperation", "R.id.operationsIdsList${id}: ${button.text}}")
                calculate(button.text as String)
            })
        }

    }
}
