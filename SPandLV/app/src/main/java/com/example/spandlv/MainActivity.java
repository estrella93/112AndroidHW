package com.example.spandlv;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner foodSpinner;
    private ListView listView1, listView2, listView3;
    private TextView selectedMain, selectedSide, selectedDrink;
    private ArrayAdapter<CharSequence> adapter;

    private String selectedMainText = "";
    private String selectedSideText = "";
    private String selectedDrinkText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodSpinner = findViewById(R.id.food);
        listView1 = findViewById(R.id.listview1);
        listView2 = findViewById(R.id.listview2);
        listView3 = findViewById(R.id.listview3);
        selectedMain = findViewById(R.id.selectedMain);
        selectedSide = findViewById(R.id.selectedSide);
        selectedDrink = findViewById(R.id.selectedDrink);

        // Initially hide listView2 and listView3
        listView2.setVisibility(View.GONE);
        listView3.setVisibility(View.GONE);



        foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 主餐
                        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.main, android.R.layout.simple_list_item_1);
                        listView1.setAdapter(adapter);
                        listView1.setVisibility(View.VISIBLE);
                        listView2.setVisibility(View.GONE);
                        listView3.setVisibility(View.GONE);
                        break;
                    case 1: // 附餐
                        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.side, android.R.layout.simple_list_item_1);
                        listView2.setAdapter(adapter);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.VISIBLE);
                        listView3.setVisibility(View.GONE);
                        break;
                    case 2: // 飲料
                        adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.drink, android.R.layout.simple_list_item_1);
                        listView3.setAdapter(adapter);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.GONE);
                        listView3.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMainText = parent.getItemAtPosition(position).toString();
                selectedMain.setText(selectedMainText);
                updateSelectedMain();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSideText =parent.getItemAtPosition(position).toString();
                selectedSide.setText(selectedSideText);
                updateSelectedSide();
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDrinkText = parent.getItemAtPosition(position).toString();
                selectedDrink.setText(selectedDrinkText);
                updateSelectedDrink();
            }
        });
    }

    private void updateSelectedMain() {
        String displayText = "主餐: " + (selectedMainText.isEmpty() ? "請選擇" : selectedMainText);
        selectedMain.setText(displayText);
    }
    private void updateSelectedSide() {
        String displayText = "附餐: " + (selectedSideText.isEmpty() ? "請選擇" : selectedSideText);
        selectedSide.setText(displayText); // Change to selectedSide
    }

    private void updateSelectedDrink() {
        String displayText = "飲料: " + (selectedDrinkText.isEmpty() ? "請選擇" : selectedDrinkText);
        selectedDrink.setText(displayText); // Change to selectedDrink
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
