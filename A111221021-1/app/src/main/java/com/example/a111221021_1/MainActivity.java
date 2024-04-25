package com.example.a111221021_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup genderRadioGroup, ticketTypeRadioGroup;
    private EditText quantityEditText;
    private TextView resultTextView;
    private List<String> orderDetailsList;
    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        ticketTypeRadioGroup = findViewById(R.id.ticketTypeRadioGroup);
        quantityEditText = findViewById(R.id.quantityEditText);
        resultTextView = findViewById(R.id.resultTextView);
        orderDetailsList = new ArrayList<>();

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOrderDetails();
            }
        });

        ticketTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOrderDetails();
            }
        });

        quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateOrderDetails();
            }
        });

        updateOrderDetails();
    }

    private void updateOrderDetails() {
        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityEditText.getText().toString());
        } catch (NumberFormatException e) {}

        RadioButton selectedGenderRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender = selectedGenderRadioButton.getText().toString();

        RadioButton selectedTicketTypeRadioButton = findViewById(ticketTypeRadioGroup.getCheckedRadioButtonId());
        String ticketType = selectedTicketTypeRadioButton.getText().toString();

        int total = calculateTotal(ticketType, quantity);

        orderDetailsList.clear();
        String orderDetails = "性別: " + gender + "\n" +
                "票種: " + ticketType + " x" + quantity + "\n" +
                "金額: " + total + " 元\n\n";
        orderDetailsList.add(orderDetails);

        totalAmount = total;

        displayOrders();

        // Start the ConfirmationActivity
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("totalAmount", totalAmount); // Pass the total amount to the next activity
        startActivity(intent);
    }

    private int calculateTotal(String ticketType, int quantity) {
        int price;
        if (ticketType.contains("成人")) {
            price = 500;
        } else if (ticketType.contains("孩童")) {
            price = 250;
        } else {
            price = 400;
        }
        return price * quantity;
    }

    private void displayOrders() {
        StringBuilder result = new StringBuilder();
        for (String order : orderDetailsList) {
            result.append(order);
        }
        resultTextView.setText(result.toString());
    }
}
