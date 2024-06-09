package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtHeight, edtWeight;
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        txvShow = findViewById(R.id.txvShow);

        Button btnCalc = findViewById(R.id.btnCalc);
        Button btnClear = findViewById(R.id.btnClear);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = edtHeight.getText().toString();
        String weightStr = edtWeight.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            txvShow.setText("請輸入身高和體重");
            return;
        }

        try {
            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (height <= 0 || weight <= 0) {
                txvShow.setText("身高和體重必須大於零");
            } else {
                double bmi = calculateBMI(height, weight);
                String bmiText = String.format("您的BMI指數為 %.2f", bmi);
                txvShow.setText(bmiText);
            }
        } catch (NumberFormatException e) {
            txvShow.setText("輸入錯誤，請輸入數字");
        }
    }

    private double calculateBMI(double height, double weight) {
        // BMI calculation formula
        return weight / ((height / 100) * (height / 100));
    }

    private void clearInputs() {
        edtHeight.setText("");
        edtWeight.setText("");
        txvShow.setText("警語");
    }
}
