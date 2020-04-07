package com.woowrale.calculator.kapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woowrale.calculator.kapp.usecase.CalculatorDivisionUseCase
import com.woowrale.calculator.kapp.usecase.CalculatorMinusUseCase
import com.woowrale.calculator.kapp.usecase.CalculatorMultiplyUseCase
import com.woowrale.calculator.kapp.usecase.CalculatorPlusUseCase

class CalculatorViewModel : ViewModel() {
    private var result: MutableLiveData<Double>? = null

    init {
        result = MutableLiveData()
    }

    fun getResult(): LiveData<Double>? {
        return result
    }

    private fun doPlusOperation(
        numberOne: Double,
        numberTwo: Double
    ) {
        result?.value = CalculatorPlusUseCase().executePlus(numberOne, numberTwo)
    }

    private fun doMinusOperation(
        numberOne: Double,
        numberTwo: Double
    ) {
        result?.value = CalculatorMinusUseCase().executeMinus(numberOne, numberTwo)
    }

    private fun doMultiplyOperation(
        numberOne: Double,
        numberTwo: Double
    ) {
        result?.value = CalculatorMultiplyUseCase().executeMultiply(numberOne, numberTwo)
    }

    private fun doDivisionOperation(
        numberOne: Double,
        numberTwo: Double
    ) {
        result?.value = CalculatorDivisionUseCase().executeDivision(numberOne, numberTwo)
    }

    fun executeOperation(
        numberOne: Double,
        numberTwo: Double,
        operator: String?
    ) {
        when (operator) {
            "+" -> doPlusOperation(numberOne, numberTwo)
            "-" -> doMinusOperation(numberOne, numberTwo)
            "*" -> doMultiplyOperation(numberOne, numberTwo)
            "/" -> doDivisionOperation(numberOne, numberTwo)
        }
    }
}