package com.woowrale.calculator.kapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.woowrale.calculator.kapp.mvvm.CalculatorViewModel
import kotlinx.android.synthetic.main.activity_main.*

class CalculatorActivity : AppCompatActivity() {

    private var editResult = ""
    private var operator = ""
    private var numberOne = 0.0
    private var numberTwo = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val viewModel = ViewModelProviders.of(this).get<CalculatorViewModel>(CalculatorViewModel::class.java)

        btnZero.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_zero)
            editTextResult.setText(editResult)
        })

        btnOne.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_one)
            editTextResult.setText(editResult)
        })

        btnTwo.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_two)
            editTextResult.setText(editResult)
        })

        btnThree.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_three)
            editTextResult.setText(editResult)
        })

        btnFour.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_four)
            editTextResult.setText(editResult)
        })

        btnFive.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_five)
            editTextResult.setText(editResult)
        })

        btnSix.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_six)
            editTextResult.setText(editResult)
        })

        btnSeven.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_seven)
            editTextResult.setText(editResult)
        })

        btnEigth.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_eight)
            editTextResult.setText(editResult)
        })

        btnNine.setOnClickListener(View.OnClickListener {
            editResult += getString(R.string.text_nine)
            editTextResult.setText(editResult)
        })

        btnPlus.setOnClickListener(View.OnClickListener {
            operator = getString(R.string.text_plus)
            numberOne = editResult.toDouble()
            resetValues()
        })

        btnMinus.setOnClickListener(View.OnClickListener {
            operator = getString(R.string.text_minus)
            numberOne = editResult.toDouble()
            resetValues()
        })

        btnMultiply.setOnClickListener(View.OnClickListener {
            operator = getString(R.string.text_multiply)
            numberOne = editResult.toDouble()
            resetValues()
        })

        btnDivision.setOnClickListener(View.OnClickListener {
            operator = getString(R.string.text_division)
            numberOne = editResult.toDouble()
            resetValues()
        })

        btnEquals.setOnClickListener(View.OnClickListener {
            numberTwo = editResult.toDouble()
            viewModel.executeOperation(numberOne, numberTwo, operator)
            editResult = editTextResult.text.toString()
        })

        val observer =
            Observer { result: Double ->
                editTextResult.setText(result.toString())
            }

        viewModel.getResult()?.observe(this, observer)

    }

    private fun resetValues() {
        editResult = ""
        editTextResult.setText("")
    }
}
