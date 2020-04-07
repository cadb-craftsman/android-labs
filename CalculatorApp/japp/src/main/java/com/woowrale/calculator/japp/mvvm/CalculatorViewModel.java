package com.woowrale.calculator.japp.mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.woowrale.calculator.japp.usecase.CalculatorDivisionUseCase;
import com.woowrale.calculator.japp.usecase.CalculatorMinusUseCase;
import com.woowrale.calculator.japp.usecase.CalculatorMultiplyUseCase;
import com.woowrale.calculator.japp.usecase.CalculatorPlusUseCase;


public class CalculatorViewModel extends ViewModel {

    private MutableLiveData<Double> result;

    public CalculatorViewModel(){
        result = new MutableLiveData<>();
    }

    public LiveData<Double> getResult() {
        return result;
    }

    private void doPlusOperation(Double numberOne, Double numberTwo){
        result.setValue(new CalculatorPlusUseCase().executePlus(numberOne, numberTwo));
    }

    private void doMinusOperation(Double numberOne, Double numberTwo){
        result.setValue(new CalculatorMinusUseCase().executeMinus(numberOne, numberTwo));
    }

    private void doMultiplyOperation(Double numberOne, Double numberTwo){
        result.setValue(new CalculatorMultiplyUseCase().executeMultiply(numberOne, numberTwo));
    }

    private void doDivisionOperation(Double numberOne, Double numberTwo){
        result.setValue(new CalculatorDivisionUseCase().executeDivision(numberOne, numberTwo));
    }

    public void executeOperation(Double numberOne, Double numberTwo, String operator){
        switch (operator){
            case "+":
                doPlusOperation(numberOne, numberTwo);
                break;
            case "-":
                doMinusOperation(numberOne, numberTwo);
                break;
            case "*":
                doMultiplyOperation(numberOne, numberTwo);
                break;
            case "/":
                doDivisionOperation(numberOne, numberTwo);
                break;
        }
    }
}
