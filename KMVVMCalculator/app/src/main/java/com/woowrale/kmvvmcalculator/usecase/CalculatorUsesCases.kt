package com.woowrale.kmvvmcalculator.usecase

class CalculatorPlusUseCase{
    fun executePlus(numberOne: Double, numberTwo: Double): Double {
        return numberOne + numberTwo
    }
}

class CalculatorMinusUseCase{
    fun executeMinus(numberOne: Double, numberTwo: Double): Double {
        return numberOne - numberTwo
    }
}

class CalculatorMultiplyUseCase{
    fun executeMultiply(numberOne: Double, numberTwo: Double): Double {
        return numberOne * numberTwo
    }
}


class CalculatorDivisionUseCase{
    fun executeDivision(numberOne: Double, numberTwo: Double): Double {
        return numberOne / numberTwo
    }
}
