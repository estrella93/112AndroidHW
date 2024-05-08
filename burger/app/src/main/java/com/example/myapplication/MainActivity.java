package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    private ImageView image1, image2, image3, image4,image5;
    private CheckBox chk1, chk2, chk3, chk4, chk5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = findViewById(R.id.imgOutput1);
        image2 = findViewById(R.id.imgOutput2);
        image3 = findViewById(R.id.imgOutput3);
        image4 = findViewById(R.id.imgOutput4);
        image5 = findViewById(R.id.imgOutput5);

        image1.setVisibility(ImageView.GONE);
        image2.setVisibility(ImageView.GONE);
        image3.setVisibility(ImageView.GONE);
        image4.setVisibility(ImageView.GONE);
        image5.setVisibility(ImageView.GONE);

        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    image1.setVisibility(ImageView.VISIBLE);
                } else {
                    image1.setVisibility(ImageView.GONE);
                }
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    image2.setVisibility(ImageView.VISIBLE);
                } else {
                    image2.setVisibility(ImageView.GONE);
                }
            }
        });

        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    image3.setVisibility(ImageView.VISIBLE);
                } else {
                    image3.setVisibility(ImageView.GONE);
                }
            }
        });

        chk4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    image4.setVisibility(ImageView.VISIBLE);
                } else {
                    image4.setVisibility(ImageView.GONE);
                }
            }
        });
        chk5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    image5.setVisibility(ImageView.VISIBLE);
                } else {
                    image5.setVisibility(ImageView.GONE);
                }
            }
        });
    }
}
