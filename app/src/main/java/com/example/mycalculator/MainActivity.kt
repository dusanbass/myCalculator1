package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {


    var accumulator: Double = +0.0
    var current: Double = +0.0
    var currentOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun handleEquals(textViewCalculator: TextView) {
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
                if (accumulator == 0.0 && current == 0.0) {
                    textViewCalculator.text = "Undefined"
                    return
                } else if (accumulator == 0.0 || current == 0.0) {
                    if (accumulator > 0 || current > 0) {
                        textViewCalculator.text = "Infinity"
                    } else {
                        textViewCalculator.text = "-Infinity"
                    }
                    currentOperation = "="
                    return
                }
                accumulator /= current
            }
        }
        current = accumulator
        textViewCalculator.text = "${accumulator}"
        currentOperation = "="
    }

    fun calculate(operator: String, textViewCalculator: TextView) {
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
                handleEquals(textViewCalculator)
            }
        }
    }

    fun operationClick(view: View) {
        var textViewCalculator = findViewById<TextView>(R.id.textCalculator)
        val buttonId = view.id
        val button: Button = findViewById(buttonId)
        calculate(button.text as String, textViewCalculator)
    }

    fun digitClick(view: View) {
        var textViewCalculator = findViewById<TextView>(R.id.textCalculator)
        val buttonId = view.id
        val button: Button = findViewById(buttonId)
        val buttonText = button.text.toString()
        if (current == 0.0) {
            textViewCalculator.text = buttonText
            current = (buttonText as String).toDouble()
        } else {
            textViewCalculator.text = "${textViewCalculator.text}${buttonText}"
            current = (textViewCalculator.text as String).toDouble()
        }
    }
}
