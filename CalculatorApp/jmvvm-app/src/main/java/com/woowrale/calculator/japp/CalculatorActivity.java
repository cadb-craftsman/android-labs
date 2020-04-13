package com.woowrale.calculator.japp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.woowrale.calculator.japp.mvvm.CalculatorViewModel;

public class CalculatorActivity extends AppCompatActivity {

    private Button btnZero;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnSeven;
    private Button btnEigth;
    private Button btnNine;

    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivision;
    private Button btnEquals;

    private EditText editTextResult;

    private String editResult = "";
    private String operator = "";
    private Double numberOne = 0.0;
    private Double numberTwo = 0.0;

    CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        editTextResult = findViewById(R.id.editTextResult);

        btnZero = findViewById(R.id.btnZero);
        btnZero.setOnClickListener((v) -> {
            editResult += getString(R.string.text_zero);
            editTextResult.setText(editResult);
        });

        btnOne = findViewById(R.id.btnOne);
        btnOne.setOnClickListener((v) -> {
            editResult +=  getString(R.string.text_one);
            editTextResult.setText(editResult);
        });

        btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener((v) -> {
            editResult +=  getString(R.string.text_two);
            editTextResult.setText(editResult);
        });

        btnThree = findViewById(R.id.btnThree);
        btnThree.setOnClickListener((v) -> {
            editResult += getString(R.string.text_three);
            editTextResult.setText(editResult);
        });

        btnFour = findViewById(R.id.btnFour);
        btnFour.setOnClickListener((v) -> {
            editResult += getString(R.string.text_four);
            editTextResult.setText(editResult);
        });

        btnFive = findViewById(R.id.btnFive);
        btnFive.setOnClickListener((v) -> {
            editResult += getString(R.string.text_five);
            editTextResult.setText(editResult);
        });

        btnSix = findViewById(R.id.btnSix);
        btnSix.setOnClickListener((v) -> {
            editResult += getString(R.string.text_six);
            editTextResult.setText(editResult);
        });

        btnSeven = findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener((v) -> {
            editResult += getString(R.string.text_seven);
            editTextResult.setText(editResult);
        });

        btnEigth = findViewById(R.id.btnEigth);
        btnEigth.setOnClickListener((v) -> {
            editResult += getString(R.string.text_eight);
            editTextResult.setText(editResult);
        });

        btnNine = findViewById(R.id.btnNine);
        btnNine.setOnClickListener((v) -> {
            editResult += getString(R.string.text_nine);
            editTextResult.setText(editResult);
        });

        btnPlus = findViewById(R.id.btnPlus);
        btnPlus.setOnClickListener((v) -> {
            operator = getString(R.string.text_plus);
            numberOne = Double.parseDouble(editResult);
            resetValues();
        });

        btnMinus = findViewById(R.id.btnMinus);
        btnMinus.setOnClickListener((v) -> {
            operator = getString(R.string.text_minus);
            numberOne = Double.parseDouble(editResult);
            resetValues();
        });

        btnMultiply = findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener((v) -> {
            operator = getString(R.string.text_multiply);
            numberOne = Double.parseDouble(editResult);
            resetValues();
        });

        btnDivision = findViewById(R.id.btnDivision);
        btnDivision.setOnClickListener((v) -> {
            operator = getString(R.string.text_division);
            numberOne = Double.parseDouble(editResult);
            resetValues();
        });

        btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener((v) -> {
            numberTwo = Double.parseDouble(editResult);
            viewModel.executeOperation(numberOne, numberTwo, operator);
            editResult = editTextResult.getText().toString();
        });

        final Observer<Double> observer = (result) ->{
            editTextResult.setText(String.valueOf(result));
        };

        viewModel.getResult().observe(this, observer);
    }

    private void resetValues(){
        editResult = "";
        editTextResult.setText("");
    }
}
