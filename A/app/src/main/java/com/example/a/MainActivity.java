package com.example.a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

enum State {FirstNumberInput, OperatorInputed, NumberInput}
enum OP { None, Add, Sub, Mul, Div}

public class MainActivity extends AppCompatActivity {

    private double theValue = 0;
    private double operand1 = 0, operand2 = 0;
    private OP op = OP.None;
    private State state = State.FirstNumberInput;
    private boolean decimalClicked = false;
    private String currentInput = "";

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
                if (state == State.OperatorInputed) {
                    edt.setText("");  // Clear display for new operand
                    state = State.NumberInput;
                    currentInput = "";
                    decimalClicked = false; // Reset decimal for new input
                }
                currentInput += bstr;
                theValue = Double.parseDouble(currentInput);
                edt.setText(currentInput);
                break;
            case ".":
                if (!decimalClicked) {
                    decimalClicked = true;
                    if (currentInput.equals("")) {
                        currentInput = "0.";
                    } else {
                        currentInput += ".";
                    }
                    edt.setText(currentInput);
                }
                break;
            case "Clear":
                state = State.FirstNumberInput;
                theValue = 0;
                decimalClicked = false;
                currentInput = "";
                edt.setText("0");
                op = OP.None;
                operand2 = operand1 = 0;
                break;
            case "Back":
                if (currentInput.length() > 0) {
                    char lastChar = currentInput.charAt(currentInput.length() - 1);
                    if (lastChar == '.') {
                        decimalClicked = false;
                    }
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    if (currentInput.length() == 0 || currentInput.equals("-")) {
                        theValue = 0;
                        edt.setText("0");
                    } else {
                        theValue = Double.parseDouble(currentInput);
                        edt.setText(currentInput);
                    }
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (state == State.NumberInput || state == State.FirstNumberInput) {
                    operand1 = theValue;
                } else if (state == State.OperatorInputed) {
                    operand2 = theValue;
                    performOperation();
                    operand1 = theValue;
                }
                op = getOperator(bstr); // Update operator
                state = State.OperatorInputed;
                theValue = 0; // Reset the value for the next operand
                decimalClicked = false;
                currentInput = "";
                break;
            case "=":
                if (state == State.NumberInput || state == State.OperatorInputed) {
                    operand2 = theValue;
                    performOperation();
                    edt.setText(String.valueOf(operand1));
                    state = State.FirstNumberInput;
                    theValue = operand1; // Keep the result for further calculations
                    decimalClicked = false; // Reset decimal state
                    currentInput = String.valueOf(operand1);
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
                    // Handle division by zero error, you can show a message to the user
                    operand1 = 0;
                }
                break;
        }
        theValue = operand1;
        op = OP.None; // Reset the operator after the operation
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
