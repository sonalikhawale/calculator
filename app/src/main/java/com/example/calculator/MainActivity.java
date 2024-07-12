package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView solution, result;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        solution = findViewById(R.id.solution);
        result = findViewById(R.id.result);

        MaterialButton buttonAC = findViewById(R.id.button_AC);
        MaterialButton buttonBackspace = findViewById(R.id.backspace);
        MaterialButton buttonRight = findViewById(R.id.button_right);
        MaterialButton buttonDivide = findViewById(R.id.button_divivde);
        MaterialButton buttonMultiply = findViewById(R.id.button_multiply);
        MaterialButton buttonSubtract = findViewById(R.id.button_substract);
        MaterialButton buttonAdd = findViewById(R.id.button_add);
        MaterialButton buttonMod = findViewById(R.id.button_mod);
        MaterialButton buttonDot = findViewById(R.id.button_dot);
        MaterialButton buttonEqual = findViewById(R.id.button_equal);

        MaterialButton[] numberButtons = new MaterialButton[]{
                findViewById(R.id.button_0),
                findViewById(R.id.button_1),
                findViewById(R.id.button_2),
                findViewById(R.id.button_3),
                findViewById(R.id.button_4),
                findViewById(R.id.button_5),
                findViewById(R.id.button_6),
                findViewById(R.id.button_7),
                findViewById(R.id.button_8),
                findViewById(R.id.button_9)
        };

        for (MaterialButton numberButton : numberButtons) {
            numberButton.setOnClickListener(v -> {
                currentInput += ((MaterialButton) v).getText().toString();
                result.setText(currentInput);
            });
        }

        buttonAC.setOnClickListener(v -> {
            currentInput = "";
            previousInput = "";
            operator = "";
            result.setText("0");
            solution.setText("");
        });

        buttonBackspace.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                result.setText(currentInput);
            }
        });



        buttonDot.setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                currentInput += ".";
                result.setText(currentInput);
            }
        });

        View.OnClickListener operatorListener = v -> {
            if (!currentInput.isEmpty()) {
                operator = ((MaterialButton) v).getText().toString();
                previousInput = currentInput;
                currentInput = "";
                solution.setText(previousInput + " " + operator);
                isOperatorPressed = true;
            }
        };

        buttonAdd.setOnClickListener(operatorListener);
        buttonSubtract.setOnClickListener(operatorListener);
        buttonMultiply.setOnClickListener(operatorListener);
        buttonDivide.setOnClickListener(operatorListener);
        buttonMod.setOnClickListener(operatorListener);
        buttonRight.setOnClickListener(operatorListener);


        buttonEqual.setOnClickListener(v -> {
            if (!previousInput.isEmpty() && !currentInput.isEmpty() && isOperatorPressed) {
                double resultValue = calculateResult(previousInput, currentInput, operator);
                result.setText(String.valueOf(resultValue));
                solution.setText(previousInput + " " + operator + " " + currentInput);
                previousInput = String.valueOf(resultValue);
                currentInput = "";
                isOperatorPressed = false;
            }
        });
    }

    private double calculateResult(String previousInput, String currentInput, String operator) {
        double result = 0.0;
        double firstValue = Double.parseDouble(previousInput);
        double secondValue = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = firstValue / secondValue;
                }
                break;
            case "%":
                result = firstValue % secondValue;
                break;
            case "^":
                result = Math.pow(firstValue,secondValue);
                break;
        }

        return result;
    }
}