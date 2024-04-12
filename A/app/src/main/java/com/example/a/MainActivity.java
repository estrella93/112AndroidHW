package com.example.a;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

enum State {FirstNumberInput, OperatorInputed, NumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private double theValue = 0;
    private double operand1 = 0, operand2 = 0;
    private OP op = OP.None;
    private State state = State.FirstNumberInput;
    private boolean decimalClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processKeyInput(View view) {
        Button b = (Button) view;
        String bstr = b.getText().toString();
        EditText edt = findViewById(R.id.display);

        switch (bstr) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (decimalClicked) {
                    theValue = theValue + Double.parseDouble(bstr) / Math.pow(10, edt.getText().length() - edt.getText().toString().indexOf(".") - 1);
                } else {
                    theValue = theValue * 10 + Integer.parseInt(bstr);
                }
                edt.setText(String.valueOf(theValue));
                break;
            case ".":
                if (!decimalClicked) {
                    decimalClicked = true;
                    edt.append(bstr);
                }
                break;
            case "Clear":
                state = State.FirstNumberInput;
                theValue = 0;
                decimalClicked = false;
                edt.setText("0");
                op = OP.None;
                operand2 = operand1 = 0;
                break;
            case "Back":
                String currentText = edt.getText().toString();
                if (currentText.length() > 0) {
                    char lastChar = currentText.charAt(currentText.length() - 1);
                    if (lastChar == '.') {
                        decimalClicked = false;
                    }
                    edt.setText(currentText.substring(0, currentText.length() - 1));
                    if (currentText.length() == 1 || currentText.equals("-")) {
                        theValue = 0;
                    } else {
                        theValue = Double.parseDouble(edt.getText().toString());
                    }
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                switch (state) {
                    case FirstNumberInput:
                        operand1 = theValue;
                        break;
                    case OperatorInputed:
                        operand1 = theValue;
                        break;
                    case NumberInput:
                        operand2 = theValue;
                        performOperation();
                        break;
                }
                op = getOperator(bstr); // Update operator
                state = State.OperatorInputed;
                decimalClicked = false;
                break;
            case "=":
                if (state == State.NumberInput) {
                    operand2 = theValue;
                    performOperation();
                    edt.setText(String.valueOf(operand1));
                }
                break;
        }
    }

    private void performOperation() {
        switch (op) {
            case Add:
                operand1 += operand2;
                break;
            case Sub:
                operand1 -= operand2;
                break;
            case Mul:
                operand1 *= operand2;
                break;
            case Div:
                if (operand2 != 0) {
                    operand1 /= operand2;
                } else {
                    // Handle division by zero error
                }
                break;
        }
        theValue = operand1;
    }

    private OP getOperator(String buttonText) {
        switch (buttonText) {
            case "+":
                return OP.Add;
            case "-":
                return OP.Sub;
            case "*":
                return OP.Mul;
            case "/":
                return OP.Div;
            default:
                return OP.None;
        }
    }
}
