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
    private TextView selectedFood;
    private ArrayAdapter<CharSequence> adapter;

    private String selectedMain = "";
    private String selectedSide = "";
    private String selectedDrink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodSpinner = findViewById(R.id.food);
        listView1 = findViewById(R.id.listview1);
        listView2 = findViewById(R.id.listview2);
        listView3 = findViewById(R.id.listview3);
        selectedFood = findViewById(R.id.selectedFood);

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
                selectedMain = parent.getItemAtPosition(position).toString();
                updateSelectedFood();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSide = parent.getItemAtPosition(position).toString();
                updateSelectedFood();
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDrink = parent.getItemAtPosition(position).toString();
                updateSelectedFood();
            }
        });
    }

    private void updateSelectedFood() {
        String displayText = "主餐: " + (selectedMain.isEmpty() ? "請選擇" : selectedMain) + "\n" +
                "附餐: " + (selectedSide.isEmpty() ? "請選擇" : selectedSide) + "\n" +
                "飲料: " + (selectedDrink.isEmpty() ? "請選擇" : selectedDrink);
        selectedFood.setText(displayText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
