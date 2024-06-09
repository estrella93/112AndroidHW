package com.example.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private TextView txvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txvMessage = findViewById(R.id.txvMessage);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (containsChinese(username) || containsChinese(password)) {
            txvMessage.setText("輸入錯誤，請輸入英文、特殊字符或數字");
        } else if (username.isEmpty() || password.isEmpty()) {
            txvMessage.setText("帳號或密碼不能為空");
        } else if (username.equals("account") && password.equals("password")) {
            txvMessage.setText("登入成功");
        } else {
            txvMessage.setText("帳號或密碼錯誤");
        }
    }

    private boolean containsChinese(String input) {
        // Regular expression to match Chinese characters
        String regex = "[\u4e00-\u9fa5]";
        return input.matches(".*" + regex + ".*");
    }

}
